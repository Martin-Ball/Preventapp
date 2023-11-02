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
import com.martin.preventapp.view.entities.Product

class RecommendedProductFragment : Fragment() {
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

        createLineChart()

        val products = listOf(
            Product("Queso"),
            Product("Bondiola"),
            Product("Jamon"),
            Product("Mayonesa"),
            Product("Vino"),
            Product("Salame"),
            Product("Vacio")
        )

        val productsAdapter = ProductListAdapter(requireContext(), products)
        binding.listProducts.adapter = productsAdapter
    }

    private fun createLineChart() {
        val entries = listOf(
            Entry(0f, 100f),
            Entry(1f, 150f),
            Entry(2f, 120f),
            Entry(3f, 200f),
            Entry(4f, 180f),
            Entry(5f, 250f),
            Entry(6f, 220f),
            Entry(7f, 300f),
            Entry(8f, 280f),
            Entry(9f, 320f),
            Entry(10f, 290f),
            Entry(11f, 350f)
        )

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

        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER

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
                //Toast.makeText(applicationContext, "El cliente" + e?.y, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected() {
            }
        })

        binding.llChartContainer.addView(lineChart)

        binding.backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun getMonths(): List<String> {
        return listOf(
            "Ene", "Feb", "Mar", "Abr", "May", "Jun",
            "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
        )
    }
}