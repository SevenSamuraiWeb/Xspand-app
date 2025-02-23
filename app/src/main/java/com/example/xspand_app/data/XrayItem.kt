package com.example.xspand_app.data


import java.util.UUID
data class XrayItem(
    val scanId: String = UUID.randomUUID().toString(),   // Unique ID for the scan
    val imageUrl: String? = null,                        // URL to the X-ray image
    val patient: Patient,
    val doctor: Doctor,
    val radiologistId: String? = null,                   // If a radiologist is assigned
    val diseaseId: String? = null,                       // If there's a known disease ID
    val aiClassification: List<String> = emptyList(),                // AI classification result
    val noFindingsDetected: Boolean = false,             // Flag if no findings
    val radiologistReport: String? = null,               // Radiologist's written report
    val scanTimestamp: String? = null                    // Time of the scan
): java.io.Serializable


val dummyDoctor = Doctor(
    userId = "DOC-12345",
    fullName = "Dr. Alice Smith",
    emailAddress = "alice.smith@example.com",
    contactNumber = "+1-202-555-0147",
    userRole = UserRole.DOCTOR,
    gender = Gender.FEMALE,
    password = "securePassword",   // For demonstration only
    medicalSpecialization = "Radiology",
    qualificationDetails = "MBBS, MD",
    yearsOfExperience = 10
)

val dummyPatient1 = Patient(
    patientId = "PAT-001",
    fullName = "John Doe",
    isResident = true,
    emailAddress = "john.doe@example.com",
    contactNumber = "+1-202-555-0123",
    age = 30,
    heightCm = 175f,
    weightKg = 70f,
    gender = Gender.MALE
)

val dummyPatient2 = Patient(
    patientId = "PAT-002",
    fullName = "Jane Doe",
    isResident = false,
    emailAddress = "jane.doe@example.com",
    contactNumber = "+1-202-555-0456",
    age = 25,
    heightCm = 165f,
    weightKg = 60f,
    gender = Gender.FEMALE
)

val dummyPatient3 = Patient(
    patientId = "PAT-003",
    fullName = "John Smith",
    isResident = true,
    emailAddress = "john.smith@example.com",
    contactNumber = "+1-202-555-0789",
    age = 35,
    heightCm = 180f,
    weightKg = 80f,
    gender = Gender.MALE
)

val dummyPatient4 = Patient(
    patientId = "PAT-004",
    fullName = "Jane Smith",
    isResident = false,
    emailAddress = "jane.smith@example.com",
    contactNumber = "+1-202-555-0987",
    age = 40,
    heightCm = 170f,
    weightKg = 65f,
    gender = Gender.FEMALE
)

val sampleXRayItems = listOf(
    XrayItem(
        scanId = "SCAN-1001",
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/xspand-db058.firebasestorage.app/o/00000506_017.png?alt=media&token=99d68440-cfb4-42dc-986e-a3c810c84af8",
        patient = dummyPatient1,
        doctor = dummyDoctor,
        radiologistId = null,
        diseaseId = null,
        aiClassification = listOf("Pneumonia"), // Changed from "Pneumonia" to listOf("Pneumonia")
        noFindingsDetected = false,
        radiologistReport = null,
        scanTimestamp = "2025-02-22 14:30"
    ),
    XrayItem(
        scanId = "SCAN-1002",
        imageUrl = "https://picsum.photos/200/300?random=2",
        patient = dummyPatient2,
        doctor = dummyDoctor,
        radiologistId = "RAD-001",
        diseaseId = "DIS-100",
        aiClassification = listOf("Normal"),
        noFindingsDetected = true,
        radiologistReport = null,
        scanTimestamp = "2025-02-23 10:15"
    ),
    XrayItem(
        scanId = "SCAN-1003",
        imageUrl = "https://picsum.photos/200/300?random=3",
        patient = dummyPatient3,
        doctor = dummyDoctor,
        radiologistId = null,
        diseaseId = "DIS-101",
        aiClassification = listOf("Tuberculosis"),
        noFindingsDetected = false,
        radiologistReport = "Visible infiltration in the upper lobe.",
        scanTimestamp = "2025-02-24 09:00"
    ),
    XrayItem(
        scanId = "SCAN-1004",
        imageUrl = "https://picsum.photos/200/300?random=4",
        patient = dummyPatient4,
        doctor = dummyDoctor,
        radiologistId = "RAD-002",
        diseaseId = null,
        aiClassification = emptyList(), // Changed from null to emptyList()
        noFindingsDetected = false,
        radiologistReport = "Suspected infiltration. Needs further tests.",
        scanTimestamp = "2025-02-25 13:45"
    )
)

