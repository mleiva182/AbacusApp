package com.mleiva.abacusapp.ui.portfolio

import android.graphics.Color
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.mleiva.abacusapp.model.Performance

/***
 * Project: Abacus App
 * From: com.mleiva.abacusapp.ui.portfolio
 * Creted by: Marcelo Leiva on 24-07-2025 at 10:53
 ***/
@Composable
fun PerformanceChart(performanceData: List<Performance>, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp),
        factory = { context ->
            LineChart(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setNoDataText("Sin datos")
                axisRight.isEnabled = false
                description.isEnabled = false
                legend.isEnabled = true
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.granularity = 1f
                xAxis.labelRotationAngle = -45f
            }
        },
        update = { chart ->
            val entries = performanceData.mapIndexed { index, item ->
                Entry(index.toFloat(), item.value.toFloat())
            }

            val dataSet = LineDataSet(entries, "").apply {
                color = Color.BLUE
                valueTextColor = Color.BLACK
                lineWidth = 2f
                circleRadius = 4f
                setDrawValues(false)
            }

            chart.data = LineData(dataSet)

            // Fechas en el eje X
            chart.xAxis.valueFormatter = IndexAxisValueFormatter(performanceData.map { it.date })

            chart.invalidate()
        }
    )
}
