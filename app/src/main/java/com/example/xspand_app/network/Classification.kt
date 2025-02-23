package com.example.xspand_app.network

data class ClassificationRequest(
    val image_url: String,
    val patient_id: String,
    val doctor_id: String
)

data class ClassificationResponse(
    val message: String,
    val scan_id: String,
    val scan_details: ScanDetails
)

data class ScanDetails(
    val image_url: String,
    val patient_id: String,
    val doctor_id: String,
    val radiologist_id: String?,
    val disease_id: String?,
    val ai_classification: String,
    val no_findings_detected: String?,
    val radiologist_report: String?,
    val scan_timestamp: String,
    val disease_name: String?,
    val ai_approved: Boolean,
    val ai_confidence: String,
    val scan_id: String
)
