package com.martin.preventapp.view.fragments.admin.audit.price

import ProductListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.martin.preventapp.R
import com.martin.preventapp.controller.admin.audit.AuditController
import com.martin.preventapp.controller.seller.recommended.RecommendedController
import com.martin.preventapp.databinding.FragmentAuditUserBinding
import com.martin.preventapp.databinding.FragmentPriceAuditBinding
import com.martin.preventapp.model.Application
import com.martin.preventapp.model.entities.Response.ProductData
import com.martin.preventapp.model.entities.Response.ProductsPriceResponse
import com.martin.preventapp.view.adapter.AmountAdapter
import com.martin.preventapp.view.adapter.ProductPriceAuditAdapter
import com.martin.preventapp.view.entities.ItemAmount
import com.martin.preventapp.view.entities.Product
import com.martin.preventapp.view.entities.ProductPriceAudit
import com.martin.preventapp.view.fragments.admin.audit.user.AuditUserFragment

class PriceAuditFragment : Fragment() {
    private var _binding: FragmentPriceAuditBinding? = null
    private val binding get() = _binding!!
    private var selectedMonth: String = ""
    private val months: List<String> = getMonths()


    companion object {
        private var priceAuditFragment: PriceAuditFragment? = null
        @JvmStatic
        val instance: PriceAuditFragment?
            get() {
                if (priceAuditFragment == null) {
                    priceAuditFragment = PriceAuditFragment()
                }
                return priceAuditFragment
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPriceAuditBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AuditController.instance!!.getListProducts()

        val adapter = ArrayAdapter(requireContext(), R.layout.item_rol, months)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerMonth.adapter = adapter

        binding.spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                selectedMonth = "${position + 1}"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        binding.backButton.setOnClickListener {
            AuditController.instance!!.goToMain()
        }
    }

    fun showListProducts(list: List<Product>){
        val productsAdapter = ProductListAdapter(requireContext(), list)
        binding.productList.adapter = productsAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                if (query != null) {
                    productsAdapter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    productsAdapter.filter(newText)
                }
                return false
            }
        })

        binding.productList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedProduct = productsAdapter.getItem(position)
            AuditController.instance!!.getProductPrice(
                Application.getUserShared(requireContext()) ?: "",
                selectedMonth,
                selectedProduct.productName
            )
        }
    }

    fun showProductsPriceList(list: ProductsPriceResponse){
        if(list.productsPrice.isEmpty()){
            binding.llListPriceChange.visibility = View.GONE
            binding.tvProduct.visibility = View.GONE
            binding.tvNotProducts.visibility = View.VISIBLE
        }else{
            binding.tvNotProducts.visibility = View.GONE
            binding.llListPriceChange.visibility = View.VISIBLE
            binding.tvProduct.visibility = View.VISIBLE
            binding.tvProduct.text = "Aumento del producto ${list.productsPrice[0].productName}"
            val productsAdapter = ProductPriceAuditAdapter(requireContext(), list.productsPrice[0].data.map {
                ProductPriceAudit(
                    it.productName,
                    it.listName,
                    it.unitPrice,
                    it.dateValidity,
                    it.creationDate
                )
            })
            binding.listChangePrices.adapter = productsAdapter
        }
    }

    private fun getMonths(): List<String> {
        return listOf(
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        )
    }
}