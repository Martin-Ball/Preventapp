package com.martin.preventapp.view.fragments.recommended

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.martin.preventapp.databinding.ActivityRecommendedProductsBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.martin.preventapp.R
import com.martin.preventapp.controller.recommended.RecommendedController

class RecommendedProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecommendedProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityRecommendedProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createLineChart()

        val products = arrayOf("Queso", "Bondiola", "Jamon")

        val productsAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            products
        )

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

        val lineChart = LineChart(this)
        lineChart.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        lineChart.data = lineData

        dataSet.lineWidth = 5f
        dataSet.color = ContextCompat.getColor(this, R.color.border)

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
    }

    private fun getMonths(): List<String> {
        return listOf(
            "Ene", "Feb", "Mar", "Abr", "May", "Jun",
            "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"
        )
    }
}