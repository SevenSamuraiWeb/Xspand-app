package com.example.xspand_app.ui.Doctor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.xspand_app.data.Patient
import com.example.xspand_app.data.XrayItem
import com.example.xspand_app.data.dummyPatient1

@Composable
fun PatientDetails(patient: Patient,xRayList:List<XrayItem>){
    var prescriptions by remember { mutableStateOf("") }
    var xrayList by remember { mutableStateOf(xRayList.filter { it.patient.patientId == patient.patientId }) }

    Column(modifier=Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row(modifier=Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                Icons.Default.AccountCircle,
                contentDescription = null,
                modifier=Modifier.size(64.dp)
            )
            Column(
                modifier=Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                patient.fullName?.let { Text(text= it) }
                Spacer(modifier= Modifier.height(8.dp))
                Text(text=patient.age.toString())
            }
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Add x-ray")
        }
        Row(modifier=Modifier.padding(16.dp).horizontalScroll(rememberScrollState())) {
            xrayList.forEach{
                xray->
            PatientXray(dummyPatient1,xray,modifier=Modifier.clickable {
            })
            }
        }
        OutlinedTextField(
            value = prescriptions,
            onValueChange = {prescriptions=it},
            label = {Text("Prescriptions")}
        )
        //add signature upload
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Save")
        }
    }

}

@Composable
fun PatientXray(patient: Patient, xRay: XrayItem, modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) } // State for alert dialog visibility

    Card(
        modifier = Modifier
            .fillMaxHeight(0.3f)
            .clickable { showDialog = true }
            .border(BorderStroke(4.dp, color = Color.Black))// Show dialog when clicked
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(xRay.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
    }

    // Alert Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false }, // Close when clicked outside
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Close")
                }
            },
            title = { Text("X-Ray Details") },
            text = {
                Column {
                    Text("Scan ID: ${xRay.scanId}")
                    Text("Patient ID: ${xRay.patient.patientId}")
                    Text("Disease ID: ${xRay.diseaseId}")
                    Text("AI Classification: ${xRay.aiClassification}")
                    Text("Radiologist Report: ${xRay.radiologistReport}")
                    Text("Scan Timestamp: ${xRay.scanTimestamp}")
                }
            }
        )
    }
}




