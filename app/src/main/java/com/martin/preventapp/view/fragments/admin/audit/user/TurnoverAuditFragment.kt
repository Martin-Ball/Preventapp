package com.martin.preventapp.view.fragments.admin.audit.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
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
import com.martin.preventapp.databinding.FragmentLoginAuditBinding
import com.martin.preventapp.databinding.FragmentTurnoverAuditBinding
import com.martin.preventapp.view.entities.Turnover

class TurnoverAuditFragment : Fragment() {
    private var _binding: FragmentTurnoverAuditBinding? = null
    private val binding get() = _binding!!
    private lateinit var turnover: List<Turnover>

    companion object {
        private var turnoverAuditFragment: TurnoverAuditFragment? = null

        @JvmStatic
        fun newInstance(listTurnover: List<Turnover>): TurnoverAuditFragment {
            if (turnoverAuditFragment == null) {
                turnoverAuditFragment = TurnoverAuditFragment()
            }
            val args = Bundle().apply {
                putSerializable("turnover", ArrayList(listTurnover))
            }
            turnoverAuditFragment?.arguments = args

            return turnoverAuditFragment!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTurnoverAuditBinding.inflate(inflater)
        val turnoverList = arguments?.getSerializable("turnover") as? ArrayList<Turnover>
        if (turnoverList != null) {
            turnover = turnoverList
            createLineChart()
        }
        return binding.root
    }

    private fun createLineChart() {

        val entries = (1..12).map { month ->
            val total = turnover.firstOrNull { it.month == month }?.salesVolume ?: 0
            Entry(month.toFloat(), total.toFloat())
        }

        val dataSet = LineDataSet(entries, "Volumen de ventas mensuales")

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
                binding.tvVolumme.text = "El cliente compro en el mes ${e!!.x.toInt()} $${e.y}"
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