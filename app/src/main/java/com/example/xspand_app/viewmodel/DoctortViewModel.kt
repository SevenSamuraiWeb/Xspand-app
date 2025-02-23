package com.example.xspand_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.xspand_app.network.ApiService
import com.example.xspand_app.network.ClassificationRequest
import com.example.xspand_app.network.ClassificationResponse
import com.example.xspand_app.network.Patient
import com.example.xspand_app.network.XrayResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PatientRepository(private val apiService: ApiService) {
    suspend fun fetchPatients(): List<Patient> {
        return apiService.getPatients().patients
    }
    suspend fun getResultbyId(patientId: String):List<XrayResponse>{
        return apiService.getXrayResults(patientId)
    }

    suspend fun getClassificationResults(classificationRequest: ClassificationRequest):ClassificationResponse{
        return apiService.getClassificationResults(classificationRequest)
    }
}

class DoctorViewModel(private val repository: PatientRepository) : ViewModel() {
    private val _patientsList = MutableStateFlow<List<Patient>>(emptyList())
    val patientsList: StateFlow<List<Patient>> = _patientsList.asStateFlow()
    private val _xrayResponse = MutableStateFlow<List<XrayResponse>>(emptyList())
    val xrayResponse: StateFlow<List<XrayResponse>> = _xrayResponse.asStateFlow()

    private val _classificationResponse = MutableStateFlow<ClassificationResponse?>(null)
    val classificationResponse: StateFlow<ClassificationResponse?> = _classificationResponse.asStateFlow()


    init {
        fetchPatients()
    }

    fun fetchPatients() {
        viewModelScope.launch {
            try {
                val patients = repository.fetchPatients()
                _patientsList.value = patients
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun getPatientById(patientId: String): Patient {
        return _patientsList.value.find { it.patient_id== patientId }!!
    }
    fun getXrayScanById(patientId: String) {
        viewModelScope.launch {
            try {
                val response = repository.getResultbyId(patientId)
                _xrayResponse.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    suspend fun getClassificationResults(patientId: String, doctorId: String, imageUrl: String):String?{
        val classificationRequest = ClassificationRequest(imageUrl,patientId,doctorId)
        try {
            val response = repository.getClassificationResults(classificationRequest)
            _classificationResponse.value = response
            return response.scan_details.ai_classification
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    class Factory(private val apiService: ApiService) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DoctorViewModel::class.java)) {
                return DoctorViewModel(PatientRepository(apiService)) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}