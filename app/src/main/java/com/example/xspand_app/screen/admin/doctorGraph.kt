package com.example.xpandtester

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars

@Composable
fun DoctorGraphTwo(modifier: Modifier = Modifier){
    val gradientColors = listOf(Color(0xFF9ACBD0), Color(0xFF2973B2))
    ColumnChart(
        modifier= Modifier.padding(16.dp).fillMaxHeight(0.85f),
        data = listOf(
            Bars(
                label = "Atelectasis", values = listOf(
                    Bars.Data(value = 20.0, color = Brush.verticalGradient(gradientColors))
                )
            ),
            Bars(
                label = "Cardiomelagy", values = listOf(
                    Bars.Data(
                        value = 24.0,
                        color = Brush.verticalGradient(gradientColors)
                    )
                )
            ),
            Bars(
                label = "Consolidation", values = listOf(
                    Bars.Data(
                        value = 23.0,
                        color = Brush.verticalGradient(gradientColors)
                    )
                )
            ),
            Bars(
                label = "Thu", values = listOf(
                    Bars.Data(value = 10.0, color = Brush.verticalGradient(gradientColors))
                )
            ),
            Bars(
                label = "Edema", values = listOf(
                    Bars.Data(value = 22.0, color = Brush.verticalGradient(gradientColors))
                )
            ),
            Bars(
                label = "Effusion", values = listOf(
                    Bars.Data(value = 20.0, color = Brush.verticalGradient(gradientColors))
                )
            ),
            Bars(
                label = "Emphysema", values = listOf(
                    Bars.Data(value = 15.0, color = Brush.verticalGradient(gradientColors))
                )
            ),
            Bars(
                label = "Fibrosis", values = listOf(
                    Bars.Data(value = 15.0, color = Brush.verticalGradient(gradientColors))
                )
            ),
            Bars(
                label = "Infiltration", values = listOf(
                    Bars.Data(value = 15.0, color = Brush.verticalGradient(gradientColors))
                )
            ),
            Bars(
                label = "Mass", values = listOf(
                    Bars.Data(value = 20.0, color = Brush.verticalGradient(gradientColors))
                ),
            ),
            Bars(
                label = "Nodule", values = listOf(
                    Bars.Data(value = 15.0, color = Brush.verticalGradient(gradientColors))
                )
            ),
            Bars(
                label = "Pleural_Thickening", values = listOf(
                    Bars.Data(value = 30.0, color = Brush.verticalGradient(gradientColors))
                )
            ),
            Bars(
                label = "Pneumonia", values = listOf(
                    Bars.Data(value = 15.0, color = Brush.verticalGradient(gradientColors))
                )
            ),
            Bars(
                label = "Pneumothorax", values = listOf(
                    Bars.Data(value = 10.0, color = Brush.verticalGradient(gradientColors))
                )
            )
        ),
        barProperties = BarProperties(
            cornerRadius = Bars.Data.Radius.Rectangle(topRight = 6.dp, topLeft = 6.dp),
            spacing = 3.dp,
            thickness = 20.dp
        ),
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
}