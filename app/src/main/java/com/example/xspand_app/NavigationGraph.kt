package com.example.xspand_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.xspand_app.data.sampleXRayItems
import com.example.xspand_app.screen.Screen
import com.example.xspand_app.screen.admin.AdminMain
import com.example.xspand_app.screen.admin.adminProfilePage
import com.example.xspand_app.screen.doctor.DoctorMain
import com.example.xspand_app.screen.doctor.doctorProfilePage
import com.example.xspand_app.screen.radiologist.RadiologistMain
import com.example.xspand_app.screen.radiologist.XRayDashboard
import com.example.xspand_app.screen.radiologist.radiologistProfilePage
import com.example.xspand_app.screen.setup.ForgotPasswordScreen
import com.example.xspand_app.screen.setup.LoginScreen
import com.example.xspand_app.screen.setup.SplashScreen
import com.example.xspand_app.ui.Admin.HomePageAdmin
import com.example.xspand_app.ui.Doctor.HomePageDoctor

@Composable
fun NavigationGraph(navController: NavHostController,modifier: Modifier =Modifier){
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route){
            SplashScreen{
                navController.navigate(Screen.LoginScreen.route)
            }
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(modifier = modifier,
                forgotPasswordClick = { navController.navigate(Screen.ForgotPasswordScreen.route) }){
                role->
                    when(role){
                        "Admin" -> navController.navigate("AdminMain")
                        "Doctor" -> navController.navigate("DoctorMain")
                        "Radiologist" -> navController.navigate("RadiologistMain")
                    }
            }
        }
        composable(Screen.ForgotPasswordScreen.route){
            ForgotPasswordScreen(
                onBackClick = { navController.popBackStack() },
                onResetSuccess = {navController.popBackStack()}
            )
        }
        composable("AdminMain") {
            AdminMain()
        }
        composable("DoctorMain") {
           DoctorMain()
        }
        composable("RadiologistMain") {
            RadiologistMain()
        }
    }
}

@Composable
fun DoctorNavGraph(navController: NavHostController,modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = Screen.DoctorBottomScreen.Home.route
    ) {
        composable(Screen.DoctorBottomScreen.Home.route){
            HomePageDoctor()
        }
        composable(Screen.DoctorBottomScreen.Profile.route){
            doctorProfilePage()
        }
    }
}
@Composable
fun AdminNavGraph(navController: NavHostController,modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = Screen.AdminBottomScreen.Home.route
    ) {
        composable(Screen.AdminBottomScreen.Home.route){
           HomePageAdmin()
        }
        composable(Screen.AdminBottomScreen.Profile.route) {
            adminProfilePage()
        }
    }
}
@Composable
fun RadiologistNavGraph(navController: NavHostController,modifier: Modifier){
    NavHost(
        navController = navController,
        startDestination = Screen.RadiologistBottomScreen.Home.route
    ) {
        composable(Screen.RadiologistBottomScreen.Home.route){
           XRayDashboard(sampleXRayItems)
        }
        composable(Screen.RadiologistBottomScreen.Profile.route){
            radiologistProfilePage()
        }
    }
}