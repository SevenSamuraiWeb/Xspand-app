package com.example.xspand_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.xspand_app.screen.HomePageDoctor
import com.example.xspand_app.screen.LoginScreen
import com.example.xspand_app.screen.SplashScreen

@Composable
fun NavigationGraph(navController: NavHostController,modifier: Modifier =Modifier){
    NavHost(
        navController = navController,
        startDestination = "splashScreen"
    ) {
        composable("splashScreen"){
            SplashScreen{
                navController.navigate("loginScreen")
            }
        }
        composable("loginScreen"){
            LoginScreen{
                navController.navigate("homePageDoctor")
            }
        }
        composable("homePageDoctor") {
            HomePageDoctor()
        }
    }
}