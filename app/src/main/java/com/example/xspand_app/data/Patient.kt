package com.example.xspand_app.data
import java.util.UUID
enum class Gender {
    MALE, FEMALE, OTHER
}

data class Patient(
    val patientId: String = "",
    val fullName: String? = null,
    val isResident: Boolean,
    val emailAddress: String? = null,
    val contactNumber: String? = null,
    val age: Int? = null,
    val heightCm: Float? = null,
    val weightKg: Float? = null,
    val gender: Gender? = null
)
