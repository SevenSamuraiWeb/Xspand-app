package com.example.xspand_app.ui.Doctor

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.xspand_app.R
import com.example.xspand_app.data.Patient
import com.example.xspand_app.data.XrayItem
import com.example.xspand_app.data.dummyPatientList
import com.example.xspand_app.data.sampleXRayItems
import com.example.xspand_app.screen.radiologist.FullScreenImageDialog
import com.example.xspand_app.viewmodel.DoctorViewModel
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PatientDetails(patientId: String,isPast: Boolean = false,doctorViewModel: DoctorViewModel) {
    doctorViewModel.getXrayScanById(patientId)
    val patient = doctorViewModel.getPatientById(patientId)
    val xrayDetail by doctorViewModel.xrayResponse.collectAsState()
    val classification = "Effusion\nPnuemonia"
    sampleXRayItems
    val isLoading = remember { mutableStateOf(false) }
    val classificationResult = remember { mutableStateOf("") }
    var doctorId = "M4QkhGVpFQhi33xG9JOR7qEA7VD2"
    var prescriptions by remember { mutableStateOf("") }
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var isUploading by remember { mutableStateOf(false) }
    var uploadStatus by remember { mutableStateOf<String?>(null) }
    var downloadUrl by remember { mutableStateOf<String?>(null) }
    var tempImageFile by remember { mutableStateOf<File?>(null) }
    val context = LocalContext.current
    fun createImageFile(): Uri {
        tempImageFile = File(
            File(context.cacheDir, "images").apply { mkdirs() },
            "temp_${System.currentTimeMillis()}.jpg"
        ).apply {
            createNewFile()
        }
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            tempImageFile!!
        )
    }

    // Gallery picker launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            imageUri = uri
            uploadStatus = null
            downloadUrl = null
        }
    }

    // Camera launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && tempImageFile != null) {
            imageUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                tempImageFile!!
            )
            uploadStatus = null
            downloadUrl = null
            Toast.makeText(context, "Image captured successfully", Toast.LENGTH_SHORT).show()
        } else {
            imageUri = null
            Toast.makeText(context, "Failed to capture image", Toast.LENGTH_SHORT).show()
        }
    }

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            try {
                imageUri = createImageFile()
                cameraLauncher.launch(imageUri!!)
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Failed to create image file: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                context,
                "Camera permission is required to take photos",
                Toast.LENGTH_LONG
            ).show()
        }
    }
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
                                text = "Patient Details",
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color.White,
                                modifier = Modifier.padding(8.dp)
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
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color.White)
                .padding(paddingValues).verticalScroll(
                    rememberScrollState()
                )
        ) {
            // Patient Basic Info
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth().padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            "Patient ID: $patientId",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            "Name: ${patient.full_name!!}",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 20.sp
                            )
                        )
                        Text(
                            "Age: ${patient.age}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            "Gender: ${patient.gender}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if(xrayDetail.isEmpty()){
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)).padding(8.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Upload the Xray",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            // Gallery Button
                            ElevatedButton(
                                onClick = {
                                    galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                }
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_gallery),
                                    contentDescription = "Gallery",
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text("Gallery")
                            }

                            // Camera Button
                            ElevatedButton(
                                onClick = {
                                    permissionLauncher.launch(android.Manifest.permission.CAMERA)
                                }
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_camera),
                                    contentDescription = "Camera",
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text("Camera")
                            }
                        }

                        // Upload Section
                        if (imageUri != null) {
                            AsyncImage(
                                model = ImageRequest.Builder(context)
                                    .data(imageUri)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "Selected Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentScale = ContentScale.Inside
                            )
                            ElevatedButton(
                                onClick = {
                                    isUploading = true
                                    uploadStatus = "Uploading..."

                                    val storage = FirebaseStorage.getInstance()
                                    val storageRef = storage.reference
                                    val imageRef = storageRef.child("images/${UUID.randomUUID()}.jpg")

                                    imageRef.putFile(imageUri!!)
                                        .addOnSuccessListener {
                                            imageRef.downloadUrl.addOnSuccessListener { url ->
                                                downloadUrl = url.toString()
                                                uploadStatus = "Upload successful!"
                                                isUploading = false
                                            }
                                        }
                                        .addOnFailureListener { e ->
                                            uploadStatus = "Upload failed: ${e.message}"
                                            isUploading = false
                                        }
                                },
                                enabled = !isUploading
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_upload),
                                    contentDescription = "Upload",
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text("AI Classification")
                            }
                        }

                        // Loading indicator
                        if (isUploading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        // Status and URL display
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                uploadStatus?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.bodyLarge,
                                        textAlign = TextAlign.Center,
                                        color = if (it.contains("failed")) MaterialTheme.colorScheme.error
                                        else MaterialTheme.colorScheme.primary
                                    )
                                }

                                downloadUrl?.let {
                                    Log.e("Download URL", it)
                                    dummyPatientList.find { it.patientId == patientId }?.xrayScan = it
                                    LaunchedEffect(downloadUrl) {
                                        downloadUrl?.let { url ->
                                            Log.e("Download URL", url)
                                            isLoading.value = true
                                            // Call your suspend function to get classification results.
                                            val result = doctorViewModel.getClassificationResults(patientId, doctorId, url)
                                            classificationResult.value = result?:""
                                            isLoading.value = false
                                            classificationResult.value = classification
                                        }
                                    }

                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }else{
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)).padding(8.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "X-ray Image",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = xrayDetail[0].image_url ?: "",
                            ),
                            modifier = Modifier
                                .size(350.dp),
                            contentDescription = "X-ray Image"
                        )
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))
            }
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)).padding(8.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(classificationResult.value.isNotEmpty()){
                        Text(
                            "AI Classification",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            classificationResult.value,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }else{
                        Text(
                            "AI Classification",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "No Classification",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                Spacer(modifier = Modifier.height(16.dp))

            }

            Spacer(modifier = Modifier.height(16.dp))
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)).padding(8.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Prescription",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedTextField(
                        value = prescriptions,
                        onValueChange = { prescriptions = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(), // Remove fixed height so it grows with content
                        shape = RoundedCornerShape(8.dp),
                        maxLines = Int.MAX_VALUE // Allow as many lines as needed
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Send For Verification", style = MaterialTheme.typography.labelLarge)
                }
            }
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
}




