package com.martin.preventapp.view.fragments.admin.list

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.martin.preventapp.R
import com.martin.preventapp.view.entities.Product
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream

class ListSelectionActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_list_selection)
        openFilePicker()
    }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "*/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        launcher.launch(intent)
    }

    private fun readExcelFile(uri: android.net.Uri) {
        try {
            val inputStream = contentResolver.openInputStream(uri)
            val workbook: Workbook = XSSFWorkbook(inputStream)
            val sheet: Sheet = workbook.getSheetAt(0)

            val listaProductos: MutableList<Product> = mutableListOf()

            for (i in 1 until sheet.physicalNumberOfRows) {
                val row: Row = sheet.getRow(i)
                val nombreProducto: String = row.getCell(0).stringCellValue
                val precio: Double = row.getCell(1).numericCellValue

                val producto = Product(nombreProducto)
                listaProductos.add(producto)
            }

            // Aquí tienes la lista de productos. Puedes hacer lo que necesites con ella.
            for (producto in listaProductos) {
                println("Nombre: ${producto.title}, Precio: ${producto.title}")
            }

            inputStream?.close()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al leer el archivo Excel", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

}