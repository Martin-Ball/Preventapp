package com.martin.preventapp.view.fragments.admin.list

import ClientAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.martin.preventapp.controller.admin.interfaces.ListControllerInterface
import com.martin.preventapp.controller.admin.lists.ListController
import com.martin.preventapp.databinding.ActivityClientListBinding
import com.martin.preventapp.view.entities.Client
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

class ClientListActivity : AppCompatActivity(), ListControllerInterface.View {

    private lateinit var binding: ActivityClientListBinding

    private lateinit var clientList: List<Client>

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
        binding = ActivityClientListBinding.inflate(layoutInflater);
        setContentView(binding.root)

        ListController.instance!!.setContext(this)
        ListController.instance!!.setView(this)

        val intent = intent
        if (intent != null && intent.extras != null) {
            val bundle = intent.extras!!
            val isView = bundle.getBoolean("isView", false)

            if(isView){
                binding.btnCreateClients.visibility = View.GONE

                val productsAdapter = ClientAdapter(this, ListController.instance!!.getListClient().clientList)
                binding.clientList.adapter = productsAdapter
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

        binding.btnCreateClients.setOnClickListener {
            ListController.instance!!.createListClient(clientList)
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

            val clientsListXls: MutableList<Client> = mutableListOf()

            for (i in 0 until sheet.physicalNumberOfRows) {
                val row: Row = sheet.getRow(i)
                val clientName: String = row.getCell(0).stringCellValue
                val address: String = row.getCell(1).stringCellValue
                val deliveryHour: String = row.getCell(2).stringCellValue

                val client = Client(clientName, address, deliveryHour)
                clientsListXls.add(client)
            }

            clientList = clientsListXls

            val productsAdapter = ClientAdapter(this, clientList)
            binding.clientList.adapter = productsAdapter

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
}