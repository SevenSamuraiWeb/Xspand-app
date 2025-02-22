package com.example.xspand_app.data

import java.util.UUID

enum class UserRole {
    ADMIN, DOCTOR, PATIENT
}
data class Doctor(
    val userId: String = UUID.randomUUID().toString(), // Unique ID for the doctor
    val fullName: String? = null,
    val emailAddress: String? = null,
    val contactNumber: String? = null,
    val userRole: UserRole = UserRole.DOCTOR, // Default to DOCTOR role
    val gender: Gender? = null,
    val password: String? = null, // Consider security best practices for storing passwords
    val medicalSpecialization: String? = null,
    val qualificationDetails: String? = null,
    val yearsOfExperience: Int? = null
)
