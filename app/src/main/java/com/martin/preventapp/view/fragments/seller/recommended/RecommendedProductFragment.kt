package com.martin.preventapp.view.fragments.seller.recommended

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
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.martin.preventapp.R
import com.martin.preventapp.controller.seller.recommended.RecommendedController
import com.martin.preventapp.databinding.FragmentRecommendedBinding
import com.martin.preventapp.databinding.FragmentRecommendedProductBinding
import com.martin.preventapp.model.entities.Response.RecommendedResponse
import com.martin.preventapp.view.entities.Product

class RecommendedProductFragment : Fragment() {
    private lateinit var recommendedResponse: RecommendedResponse
    companion object {
        private var recommendedProductFragment: RecommendedProductFragment? = null
        @JvmStatic
        val instance: RecommendedProductFragment?
            get() {
                if (recommendedProductFragment == null) {
                    recommendedProductFragment = RecommendedProductFragment()
                }
                return recommendedProductFragment
            }
    }

    private var _binding: FragmentRecommendedProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendedProductBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recommendedResponse = RecommendedController.instance!!.getRecommendedResponse()
        setViewRecommended()
    }

    private fun setViewRecommended(){

        val productsAdapter = ProductListAdapter(requireContext(),
            recommendedResponse.products.map { Product(
                it.productName,
                it.brand,
                it.presentation,
                it.unit,
                it.price
            ) })
        binding.listProducts.adapter = productsAdapter

        createLineChart()
    }

    private fun createLineChart() {

        val entries = (1..12).map { month ->
            val total = recommendedResponse.monthlyPurchases.firstOrNull { it.mes == month }?.total ?: 0
            Entry(month.toFloat(), total.toFloat())
        }

        val dataSet = LineDataSet(entries, "Compras mensuales")

        val lineData = LineData(dataSet)

        val lineChart = LineChart(requireContext())
        lineChart.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        lineChart.data = lineData

        dataSet.lineWidth = 5f
        dataSet.color = ContextCompat.getColor(requireContext(), R.color.border)

        dataSet.mode = LineDataSet.Mode.LINEAR

        lineChart.setDrawGridBackground(false)
        lineChart.setDrawBorders(false)

        val xAxis = lineChart.xAxis
        val yAxisLeft = lineChart.axisLeft
        val yAxisRight = lineChart.axisRight


        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(getMonths())
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)


        yAxisLeft.setDrawAxisLine(false)
        yAxisLeft.setDrawLabels(false)
        yAxisLeft.setDrawGridLines(false)
        yAxisRight.isEnabled = false

        lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                binding.tvPurchases.text = "El cliente ${RecommendedController.instance?.getClientSelected()} compro en el mes ${e?.x} $ ${e?.y}"
            }

            override fun onNothingSelected() {
            }
        })

        binding.llChartContainer.addView(lineChart)

        binding.backButton.setOnClickListener {
            RecommendedController.instance!!.goToMain()
        }
    }

    private fun getMonths(): List<String> {
        return listOf(
            "Ene", "Feb", "Mar", "Abr", "May", "Jun",
            "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
        )
    }
}