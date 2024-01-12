package com.martin.preventapp.view.fragments.admin.neworders

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.interfaces.NewOrderInterface
import com.martin.preventapp.controller.admin.orders.NewOrdersController
import com.martin.preventapp.databinding.FragmentOrdersAdminBinding
import com.martin.preventapp.view.adapter.NewOrdersAdapter
import com.martin.preventapp.view.entities.NewOrder
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class OrdersAdminFragment : Fragment(), NewOrderInterface.ViewOrders {
    private var _binding: FragmentOrdersAdminBinding? = null
    private val binding get() = _binding!!

    private var isEnabled: Boolean = true
    private lateinit var listOrders: List<NewOrder>
    private lateinit var adapter: NewOrdersAdapter

    companion object {
        private var ordersAdminFragment: OrdersAdminFragment? = null
        @JvmStatic
        val instance: OrdersAdminFragment?
            get() {
                if (ordersAdminFragment == null) {
                    ordersAdminFragment = OrdersAdminFragment()
                }
                return ordersAdminFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersAdminBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NewOrdersController.instance!!.getNewOrders(true)

        binding.btnExport.setOnClickListener {
            try {
                if(adapter.getSelectedItems().isEmpty()){
                    Toast.makeText(
                        requireContext(),
                        "Debe seleccionar pedidos para exportar",
                        Toast.LENGTH_SHORT
                    ).show()
                }else {
                    generateExcelFile(adapter.getSelectedItems())
                    Toast.makeText(
                        requireContext(),
                        "Archivo Excel generado exitosamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                    adapter.clearSelection()
                }
            } catch (e: IOException) {
                println("Error al generar el archivo Excel: ${e.message}")
            }
        }

    }

    override fun showFragmentDetail() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.main_container, DetailNewOrderFragment(NewOrdersController.instance!!))
        transaction.commit()
    }

    override fun showOrdersList(list: List<NewOrder>) {
        if(list.isEmpty()){
            binding.tvEmptyOrders.visibility = View.VISIBLE
        }else{
            listOrders = list
            binding.tvEmptyOrders.visibility = View.GONE
        }

        adapter = NewOrdersAdapter(requireContext(), list, 1, NewOrdersController.instance!!)
        binding.orderList.adapter = adapter

        binding.btnConfirmOrders.setOnClickListener {
            val intent = Intent(requireContext(), ConfirmedOrdersActivity::class.java)

            intent.putExtra("isAdmin", true)

            requireActivity().startActivity(intent)
        }
    }

    private fun generateExcelFile(orders: List<NewOrder>) {
        orders.forEach {order ->
            val workbook = XSSFWorkbook()
            val sheet = workbook.createSheet("Pedido")

            val row0 = sheet.createRow(0)
            row0.createCell(0).setCellValue("Nombre del Cliente")
            row0.createCell(1).setCellValue(order.client.name)


            val row1 = sheet.createRow(1)
            row1.createCell(0).setCellValue("Direccion")
            row1.createCell(1).setCellValue(order.client.address)


            val row2 = sheet.createRow(2)
            row2.createCell(0).setCellValue("Horario de entrega")
            row2.createCell(1).setCellValue(order.client.deliveryHour)

            val headerRow = sheet.createRow(5)

            headerRow.createCell(0).setCellValue("Nombre")
            headerRow.createCell(1).setCellValue("Marca")
            headerRow.createCell(2).setCellValue("Unidad")
            headerRow.createCell(3).setCellValue("Cantidad")
            headerRow.createCell(4).setCellValue("Precio")

            var length = 0

            for ((index, product) in order.products.withIndex()) {
                val row = sheet.createRow(index + 6)
                row.createCell(0).setCellValue(product.productName)
                row.createCell(1).setCellValue(product.brand)
                row.createCell(2).setCellValue(product.unit)
                row.createCell(3).setCellValue(product.amount.toString())
                row.createCell(4).setCellValue("$${product.price}")
                length = product.productName.length
            }

            for (i in 0..3){
                sheet.setColumnWidth(i, length * 255)
            }

            val file = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS),
                "Pedido ${order.client.name.trim()}-${order.date}.xlsx")
            val outputStream = FileOutputStream(file)
            workbook.write(outputStream)
            outputStream.close()
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
    }
}