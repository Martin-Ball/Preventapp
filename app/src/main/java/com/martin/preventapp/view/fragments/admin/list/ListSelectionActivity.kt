package com.martin.preventapp.view.fragments.admin.list

import ProductListAdapter
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.martin.preventapp.controller.admin.interfaces.ListControllerInterface
import com.martin.preventapp.controller.admin.lists.ListController
import com.martin.preventapp.databinding.ActivityListSelectionBinding
import com.martin.preventapp.model.entities.ListModelEntity
import com.martin.preventapp.view.entities.Product
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ListSelectionActivity : AppCompatActivity(), ListControllerInterface.View {

    private lateinit var binding: ActivityListSelectionBinding
    private val productsList: MutableList<Product> = mutableListOf()

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
        openFilePicker()
        ListController.instance!!.setContext(this)
        ListController.instance!!.setView(this)
    }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        launcher.launch(intent)

        binding.btnCreateList.setOnClickListener {
            val listName = binding.etListName.text.toString()
            if(listName.isNotEmpty()){
                ListController.instance!!.createList(ListModelEntity(listName, productsList))
            }else{
                Toast.makeText(this, "Escriba un nombre de lista", Toast.LENGTH_LONG).show()
            }
        }

        binding.backButton.setOnClickListener {
            finish()
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
    }
}