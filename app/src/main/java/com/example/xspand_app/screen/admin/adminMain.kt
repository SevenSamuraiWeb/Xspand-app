package com.example.xspand_app.screen.admin

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.xspand_app.AdminNavGraph
import com.example.xspand_app.screen.screensInAdminBottom

@Composable
fun AdminMain() {
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
                    screensInAdminBottom.forEach { item ->
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
    ) {
            padding->
        AdminNavGraph(navController, modifier = Modifier.padding(padding))
    }
}
