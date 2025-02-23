package com.example.xspand_app.data

data class Radiologist(
    val userId:Int,
    val fullName:String,
    val emailAddress:String,
    val contactNumber:String,
    val userRole:String,
    val gender:String,
    val password:String,
    val expertiseDomain:String,
    val yearsOfExperience:Int=0
)
