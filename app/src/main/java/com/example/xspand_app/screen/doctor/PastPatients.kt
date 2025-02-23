package com.example.xspand_app.ui.Doctor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.xspand_app.data.Patient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastPatients(patients:List<Patient>, onPastClick:(String)->Unit){
    Scaffold(
        topBar = {
            Card(
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Past Patients",
                                style = MaterialTheme.typography.labelSmall,
                                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                                fontSize = 30.sp
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF48A6A7),
                        titleContentColor = Color.White
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    ){
        paddingValues ->
        // Main content
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxWidth().fillMaxHeight(fraction = 0.9f)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            items(patients){
                patient ->
                PatientCard(patient.fullName!!,patient.age!!, { onPastClick(patient.patientId!!) })
            }
        }
    }
}