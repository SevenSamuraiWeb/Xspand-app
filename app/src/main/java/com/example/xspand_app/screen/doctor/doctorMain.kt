package com.example.xspand_app.screen.doctor

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.xspand_app.DoctorNavGraph
import com.example.xspand_app.screen.screensInDoctorBottom

@Composable
fun DoctorMain() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            Card(
                modifier = Modifier.padding(bottom = 8.dp),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            ) {
                NavigationBar(
                    containerColor = Color(0xFF4DB6AC),
                ) {
                    screensInDoctorBottom.forEach { item ->
                        NavigationBarItem(
                            selected = currentRoute == item.route,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                            },
                            label = {
                                Text(text = item.title)
                            },
                        )
                    }
                }
            }
        }
    ) { padding ->
        DoctorNavGraph(navController = navController, modifier = Modifier.padding(padding))
    }
}