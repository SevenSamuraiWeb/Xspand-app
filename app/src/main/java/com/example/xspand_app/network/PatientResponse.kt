package com.example.xspand_app.network

data class PatientsResponse(
    val message: String,
    val patients: List<Patient>
)

val classificationResult = "Effusion\nPnuemonia"

data class Patient(
    val age: Int,
    val height_cm: Int,
    val patient_id: String,
    val contact_number: String,
    val email_address: String,
    val is_resident: Boolean,
    val weight_kg: Int,
    val full_name: String,
    val gender: String
)

data class XrayResponse(
    val radiologist_id: String?,
    val radiologist_report: String?,
    val patient_id: String,
    val scan_timestamp: String,
    val no_findings_detected: String?,
    val ai_confidence: String?, // new field, nullable
    val scan_id: String,
    val image_url: String,
    val doctor_id: String,
    val ai_classification: String,
    val disease_name: String?,
    val ai_approved: Boolean,
    val disease_id: String?
)


data class XrayResponses(
    val scanList:List<XrayResponse>
)