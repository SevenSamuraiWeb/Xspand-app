package com.example.xpandtester


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.xspand_app.data.Doctor
import com.example.xspand_app.data.Radiologist

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminMain() {
    var selectedDoctor by remember { mutableStateOf<Doctor?>(null) }
    var selectedRadiologist by remember { mutableStateOf<Radiologist?>(null) }
    var showAddDoctorDialog by remember { mutableStateOf(false) }
    var showAddRadiologistDialog by remember { mutableStateOf(false) }

    when {
        selectedDoctor != null -> {
            Column {
                IconButton(onClick = { selectedDoctor = null }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                //DoctorDetailsPage(doctor = selectedDoctor!!)
            }
        }

        selectedRadiologist != null -> {
            Column {
                IconButton(onClick = { selectedRadiologist = null }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                //RadiologistDetailsPage(radiologist = selectedRadiologist!!)
            }
        }

        else -> {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(
                    text = "Admin Dashboard",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4DB6AC),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { showAddDoctorDialog = true }) { Text("Add Doctor") }
                    Button(onClick = { showAddRadiologistDialog = true }) { Text("Add Radiologist") }
                }

                Spacer(modifier = Modifier.height(16.dp))

                val doctors = listOf(
                    Doctor(fullName = "Dr. Alice Smith", medicalSpecialization = "Radiology", yearsOfExperience = 10),
                    Doctor(fullName = "Dr. Bob Johnson", medicalSpecialization = "Orthopedics", yearsOfExperience = 8)
                )
//                val radiologists = listOf(
//                    Radiologist(fullName = "Dr. Emily Davis", expertiseDomain = "Chest X-rays", yearsOfExperience = 5),
//                    Radiologist(fullName = "Dr. Mark Wilson", expertiseDomain = "CT Scans", yearsOfExperience = 12)
//                )

                Text("Doctors", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                doctors.forEach { doctor -> DoctorCard(doctor) { selectedDoctor = doctor } }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Radiologists", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                //radiologists.forEach { radiologist -> RadiologistCard(radiologist) { selectedRadiologist = radiologist } }

                if (showAddDoctorDialog) AddDoctorDialog(onDismiss = { showAddDoctorDialog = false })
                if (showAddRadiologistDialog) AddRadiologistDialog(onDismiss = { showAddRadiologistDialog = false })
            }
        }
    }
}


@Composable
fun AddDoctorDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Add New Doctor", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                var fullName by remember { mutableStateOf("") }
                var specialization by remember { mutableStateOf("") }
                var experience by remember { mutableStateOf("") }

                OutlinedTextField(value = fullName, onValueChange = { fullName = it }, label = { Text("Full Name") })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = specialization, onValueChange = { specialization = it }, label = { Text("Specialization") })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = experience, onValueChange = { experience = it }, label = { Text("Experience (Years)") })

                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /* Handle adding doctor */ onDismiss() }) {
                    Text("Add Doctor")
                }
            }
        }
    }
}

@Composable
fun AddRadiologistDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Add New Radiologist", fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(16.dp))

                var fullName by remember { mutableStateOf("") }
                var expertiseDomain by remember { mutableStateOf("") }
                var experience by remember { mutableStateOf("") }

                OutlinedTextField(value = fullName, onValueChange = { fullName = it }, label = { Text("Full Name") })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = expertiseDomain, onValueChange = { expertiseDomain = it }, label = { Text("Expertise") })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = experience, onValueChange = { experience = it }, label = { Text("Experience (Years)") })

                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /* Handle adding radiologist */ onDismiss() }) {
                    Text("Add Radiologist")
                }
            }
        }
    }
}


@Composable
fun DoctorCard(doctor: Doctor, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(doctor.fullName ?: "Unknown", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("Specialization: ${doctor.medicalSpecialization ?: "N/A"}")
            Text("Experience: ${doctor.yearsOfExperience ?: 0} years")
            Text("Email: ${doctor.emailAddress ?: "N/A"}")
            Text("Contact: ${doctor.contactNumber ?: "N/A"}")
        }
    }
}

@Composable
fun RadiologistCard(radiologist: Radiologist, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(radiologist.fullName ?: "Unknown", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("Expertise: ${radiologist.expertiseDomain ?: "N/A"}")
            Text("Experience: ${radiologist.yearsOfExperience} years")
            Text("Email: ${radiologist.emailAddress ?: "N/A"}")
            Text("Contact: ${radiologist.contactNumber ?: "N/A"}")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AdminMainPreview(){
    AdminMain()
}
