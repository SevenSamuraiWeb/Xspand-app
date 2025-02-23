package com.example.xspand_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.xspand_app.data.dummyPatientList
import com.example.xspand_app.data.sampleXRayItems
import com.example.xspand_app.network.ApiService
import com.example.xspand_app.network.NetworkModule
import com.example.xspand_app.screen.Screen
import com.example.xspand_app.screen.admin.AdminMain
import com.example.xspand_app.screen.admin.adminProfilePage
import com.example.xspand_app.screen.doctor.*
import com.example.xspand_app.screen.radiologist.RadiologistMain
import com.example.xspand_app.screen.radiologist.XRayDashboard
import com.example.xspand_app.screen.radiologist.XrayPatientDetailsScreen
import com.example.xspand_app.screen.radiologist.radiologistProfilePage
import com.example.xspand_app.screen.setup.ForgotPasswordScreen
import com.example.xspand_app.screen.setup.LoginScreen
import com.example.xspand_app.screen.setup.SplashScreen
import com.example.xspand_app.ui.Admin.HomePageAdmin
import com.example.xspand_app.ui.Doctor.AddPatient
import com.example.xspand_app.ui.Doctor.HomePageDoctor
import com.example.xspand_app.ui.Doctor.PastPatients
import com.example.xspand_app.ui.Doctor.PatientDetails
import com.example.xspand_app.viewmodel.DoctorViewModel

@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen {
                navController.navigate(Screen.LoginScreen.route)
            }
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                modifier = modifier,
                forgotPasswordClick = { navController.navigate(Screen.ForgotPasswordScreen.route) }
            ) { role ->
                when (role) {
                    "Admin" -> navController.navigate("AdminMain")
                    "Doctor" -> navController.navigate("DoctorMain")
                    "Radiologist" -> navController.navigate("RadiologistMain")
                }
            }
        }
        composable(Screen.ForgotPasswordScreen.route) {
            ForgotPasswordScreen(
                onBackClick = { navController.popBackStack() },
                onResetSuccess = { navController.popBackStack() }
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
fun DoctorNavGraph(navController: NavHostController, modifier: Modifier) {
    val doctorViewModel: DoctorViewModel = viewModel(
        factory = DoctorViewModel.Factory(apiService = NetworkModule.apiService)
    )
    
    NavHost(
        navController = navController,
        startDestination = Screen.DoctorBottomScreen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.DoctorBottomScreen.Home.route) {
            HomePageDoctor(
                onAddPatientClick = {
                    navController.navigate("AddPatient")
                },
                onPatientClick = { patientId ->
                    navController.navigate(Screen.PatientDetailsScreen(patientId))
                },
                onPastPatientsClick = {
                    navController.navigate("PastPatients")
                },
                doctorViewModel = doctorViewModel
            )
        }
        composable(Screen.DoctorBottomScreen.Profile.route) {
            doctorProfilePage()
        }
        composable("AddPatient") {
            AddPatient {
                navController.popBackStack()
            }
        }
        composable("PastPatients") {
            PastPatients(dummyPatientList) { patientId ->
                navController.navigate(Screen.PatientDetailsScreen(patientId, true))
            }
        }
        composable<Screen.PatientDetailsScreen> {
            val args = it.toRoute<Screen.PatientDetailsScreen>()
            PatientDetails(patientId = args.patientId, isPast = args.isPast,doctorViewModel)
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
           XRayDashboard(sampleXRayItems){
               scanId->
               navController.navigate(
                   Screen.XrayDetailsScreen(scanId = scanId)
               )
           }
        }
        composable(Screen.RadiologistBottomScreen.Profile.route){
            radiologistProfilePage()
        }
        composable<Screen.XrayDetailsScreen>{
            val args = it.toRoute<Screen.XrayDetailsScreen>()
            XrayPatientDetailsScreen(scanId = args.scanId)
        }
    }
}