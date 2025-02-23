package com.example.xspand_app.ui.Doctor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.xspand_app.data.Patient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastPatients(patients:List<Patient>, onPastClick:()->Unit){
    TopAppBar(title = { Text("Past Patients") })
    LazyColumn(modifier=Modifier.fillMaxSize().padding(16.dp)){
        items(patients){
            patient ->
                PatientCard(patient.fullName!!,patient.age!!,onPastClick)
        }
    }
}