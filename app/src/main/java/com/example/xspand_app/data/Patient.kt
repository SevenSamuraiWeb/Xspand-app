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
    val gender: Gender? = null,
    var xrayScan: String? = null
)
val dummyPatientList = listOf(
    Patient(
        patientId = "PAT-001",
        fullName = "John Doe",
        isResident = true,
        emailAddress = "",
        contactNumber = "+1-202-555-0123",
        age = 30,
        heightCm = 175f,
        weightKg = 70f,
        gender = Gender.MALE
    ),
    Patient(
        patientId = "PAT-002",
        fullName = "Jane Doe",
        isResident = false,
        emailAddress = "",
        contactNumber = "+1-202-555-0456",
        age = 25,
        heightCm = 165f,
        weightKg = 60f,
        gender = Gender.FEMALE
    ),
    Patient(
        patientId = "PAT-002",
        fullName = "Jane Doe",
        isResident = false,
        emailAddress = "",
        contactNumber = "+1-202-555-0456",
        age = 25,
        heightCm = 165f,
        weightKg = 60f,
        gender = Gender.FEMALE
    ),
    Patient(
        patientId = "PAT-002",
        fullName = "Jane Doe",
        isResident = false,
        emailAddress = "",
        contactNumber = "+1-202-555-0456",
        age = 25,
        heightCm = 165f,
        weightKg = 60f,
        gender = Gender.FEMALE
    ),
    Patient(
        patientId = "PAT-002",
        fullName = "Jane Doe",
        isResident = false,
        emailAddress = "",
        contactNumber = "+1-202-555-0456",
        age = 25,
        heightCm = 165f,
        weightKg = 60f,
        gender = Gender.FEMALE
    ),
    Patient(
        patientId = "PAT-002",
        fullName = "Jane Doe",
        isResident = false,
        emailAddress = "",
        contactNumber = "+1-202-555-0456",
        age = 25,
        heightCm = 165f,
        weightKg = 60f,
        gender = Gender.FEMALE
    ),
    Patient(
        patientId = "PAT-002",
        fullName = "Jane Doe",
        isResident = false,
        emailAddress = "",
        contactNumber = "+1-202-555-0456",
        age = 25,
        heightCm = 165f,
        weightKg = 60f,
        gender = Gender.FEMALE
    ),
    Patient(
        patientId = "PAT-002",
        fullName = "Jane Doe",
        isResident = false,
        emailAddress = "",
        contactNumber = "+1-202-555-0456",
        age = 25,
        heightCm = 165f,
        weightKg = 60f,
        gender = Gender.FEMALE
    ),
    Patient(
        patientId = "PAT-002",
        fullName = "Jane Doe",
        isResident = false,
        emailAddress = "",
        contactNumber = "+1-202-555-0456",
        age = 25,
        heightCm = 165f,
        weightKg = 60f,
        gender = Gender.FEMALE
    ),
    Patient(
        patientId = "PAT-002",
        fullName = "Jane Doe",
        isResident = false,
        emailAddress = "",
        contactNumber = "+1-202-555-0456",
        age = 25,
        heightCm = 165f,
        weightKg = 60f,
        gender = Gender.FEMALE
    ),
)
