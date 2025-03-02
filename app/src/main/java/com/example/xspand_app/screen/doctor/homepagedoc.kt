package com.example.xspand_app.ui.Doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xspand_app.viewmodel.DoctorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageDoctor(
    onPatientClick: (String) -> Unit = {},
    onPastPatientsClick: () -> Unit = {},
    onAddPatientClick: () -> Unit = {},
    doctorViewModel: DoctorViewModel
) {
    val patients by doctorViewModel.patientsList.collectAsState()

    Scaffold(
        topBar = {
            Card(
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Patient List",
                                style = MaterialTheme.typography.labelSmall,
                                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                                fontSize = 30.sp
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF48A6A7),
                        titleContentColor = Color.White
                    )
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight().padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxHeight(0.9f)
                    .padding(8.dp).clip(RoundedCornerShape(16.dp)),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(patients) { patient ->
                    PatientCard(
                        patientName = patient.full_name ?: "",
                        age = patient.age ?: 0,
                        onClick = { onPatientClick(patient.patient_id ?: "") }
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {onPastPatientsClick()},
                    modifier = Modifier
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF48A6A7)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Past Patients", color = Color.Black)
                }
                Button(
                    onClick = {onAddPatientClick()},
                    modifier = Modifier
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF48A6A7)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text("Add Patient", color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun PatientCard(
    patientName: String,
    age: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = patientName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Age: $age",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text("Verified", color = Color.Red)
        }
    }
}