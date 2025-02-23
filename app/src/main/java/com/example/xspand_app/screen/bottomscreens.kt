package com.example.xspand_app.screen

import com.example.xspand_app.R
import com.example.xspand_app.data.XrayItem
import kotlinx.serialization.Serializable

sealed class Screen(val route:String){
    data object SplashScreen:Screen("splashScreen")
    data object LoginScreen:Screen("loginScreen")
    data object ForgotPasswordScreen:Screen("forgotPasswordScreen")
    sealed class DoctorBottomScreen(route:String, val icon:Int, val title:String):Screen(route){
        data object Home:DoctorBottomScreen("homePageDoctor", R.drawable.ic_home,"Home")
        data object Profile:DoctorBottomScreen("profileDoctor", R.drawable.ic_profile,"Profile")
    }
    sealed class AdminBottomScreen(route:String, val icon:Int, val title:String):Screen(route){
        data object Home:AdminBottomScreen("homePageAdmin", R.drawable.ic_home,"Home")
        data object Profile:AdminBottomScreen("profileAdmin", R.drawable.ic_profile,"Profile")
    }
    sealed class RadiologistBottomScreen(route:String, val icon:Int, val title:String):Screen(route){
        data object Home:RadiologistBottomScreen("homePageRadiologist", R.drawable.ic_home,"Home")
        data object Profile:RadiologistBottomScreen("profileRadiologist", R.drawable.ic_profile,"Profile")
    }
    @Serializable
    data class XrayDetailsScreen(val scanId:String)
    @Serializable
    data class PatientDetailsScreen(val patientId:String,val isPast:Boolean = false)
}
val screensInDoctorBottom = listOf(
    Screen.DoctorBottomScreen.Home,
    Screen.DoctorBottomScreen.Profile
)
val screensInAdminBottom = listOf(
    Screen.AdminBottomScreen.Home,
    Screen.AdminBottomScreen.Profile
)
val screensInRadiologistBottom = listOf(
    Screen.RadiologistBottomScreen.Home,
    Screen.RadiologistBottomScreen.Profile
)