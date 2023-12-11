package com.martin.preventapp.view.fragments.admin.list

import ProductListAdapter
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.interfaces.ListControllerInterface
import com.martin.preventapp.controller.admin.lists.ListController
import com.martin.preventapp.databinding.ActivityListSelectionBinding
import com.martin.preventapp.model.entities.ListModelEntity
import com.martin.preventapp.view.entities.Product
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.util.Calendar

class ListSelectionActivity : AppCompatActivity(), ListControllerInterface.View {

    private lateinit var binding: ActivityListSelectionBinding
    private val productsList: MutableList<Product> = mutableListOf()
    private var selectedDate: String = ""

    private val launcher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    readExcelFile(uri)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListSelectionBinding.inflate(layoutInflater);
        setContentView(binding.root)
        ListController.instance!!.setContext(this)
        ListController.instance!!.setView(this)

        val intent = intent
        if (intent != null && intent.extras != null) {
            val bundle = intent.extras!!
            val isView = bundle.getBoolean("isView", false)

            if(isView){
                binding.btnOpenDatePicker.visibility = View.GONE
                binding.btnCreateList.visibility = View.GONE
                binding.etListName.isEnabled = false

                val productsAdapter = ProductListAdapter(this, ListController.instance!!.getListPrices().listProducts)
                binding.productList.adapter = productsAdapter

                binding.etListName.setText(ListController.instance!!.getListPrices().listName)
            }
        }else{
            openFilePicker()
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        launcher.launch(intent)

        binding.btnCreateList.setOnClickListener {
            val listName = binding.etListName.text.toString()
            if(listName.isNotEmpty()){
                if(selectedDate.isNotEmpty()) {
                    ListController.instance!!.createListPrices(
                        ListModelEntity(
                            listName,
                            productsList,
                            selectedDate
                        )
                    )
                }else{
                    Toast.makeText(this, "Seleccione una fecha de vigencia", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Escriba un nombre de lista", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnOpenDatePicker.setOnClickListener {
            showDatePicker()
        }
    }

    private fun readExcelFile(uri: android.net.Uri) {
        try {
            val inputStream = contentResolver.openInputStream(uri)
            val workbook: Workbook = XSSFWorkbook(inputStream)
            val sheet: Sheet = workbook.getSheetAt(0)

            for (i in 0 until sheet.physicalNumberOfRows) {
                val row: Row = sheet.getRow(i)
                val productName: String = row.getCell(0).stringCellValue
                val brand: String = row.getCell(1).stringCellValue
                val presentation: String = row.getCell(2).stringCellValue
                val unit: String = row.getCell(3).stringCellValue
                val productPrice: Double = row.getCell(4).numericCellValue


                val producto = Product(productName, brand, presentation, unit, productPrice)
                productsList.add(producto)
            }

            val productsAdapter = ProductListAdapter(this, productsList)
            binding.productList.adapter = productsAdapter

            inputStream?.close()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al leer el archivo Excel", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            R.style.DialogTheme,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}