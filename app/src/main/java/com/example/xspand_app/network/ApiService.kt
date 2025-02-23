package com.example.xspand_app.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
//    @GET("")
//    @GET("doctors")
//    suspend fun getDoctors(): Response<DoctorResponse>
//
//    @POST("xrays/")
//    suspend fun addXRayScan(@Body request: XRayRequest): Response<XRayResponse>

    @GET("/api/v1/patients/")
    suspend fun getPatients(): PatientsResponse


    @GET("/api/v1/xrays/by_patient/{patient_id}")
    suspend fun getXrayResults(@Path("patient_id")patientId: String): List<XrayResponse>

    @POST("/api/v1/xrays/classifiy/")
    suspend fun getClassificationResults(@Body request: ClassificationRequest): ClassificationResponse
}
