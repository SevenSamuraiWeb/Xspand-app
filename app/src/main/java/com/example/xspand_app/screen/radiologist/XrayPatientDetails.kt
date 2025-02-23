package com.example.xspand_app.screen.radiologist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.example.xspand_app.data.XrayItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XrayPatientDetailsScreen(XrayItem: XrayItem) {
    var prescription by remember { mutableStateOf("") }
    var showFullScreenImage by remember { mutableStateOf(false) }
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
            modifier = Modifier
                .fillMaxSize()
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
                            "Patient ID: ${XrayItem.patient.patientId}",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            "Name: ${XrayItem.patient.fullName!!}",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 20.sp
                            )
                        )
                        Text(
                            "Age: ${XrayItem.patient.age}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            "Gender: ${XrayItem.patient.gender}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Scan Details
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
                        "${XrayItem.scanTimestamp}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        "Previous Surgeries Detail",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) //To be removed
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            // X-ray Image

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
                            model = XrayItem.imageUrl
                        ),
                        modifier = Modifier
                            .size(350.dp).clickable {
                                showFullScreenImage = true
                            },
                        contentDescription = "X-ray Image"
                    )
                }
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
                        "AI Classification Diagnosis",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Column {
                        for (classification in XrayItem.aiClassification) {
                            Text(
                                classification,
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
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
                        "Suggestion/Prescription",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    OutlinedTextField(
                        value = prescription,
                        onValueChange = { prescription = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(), // Remove fixed height so it grows with content
                        shape = RoundedCornerShape(8.dp),
                        maxLines = Int.MAX_VALUE // Allow as many lines as needed
                    )

                }
                Spacer(modifier = Modifier.height(16.dp))

                // Submit Button
                Button(
                    onClick = { /* Handle submit */ },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Approve", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
    if (showFullScreenImage) {
        FullScreenImageDialog(
            imageUrl = XrayItem.imageUrl!!,
            onDismiss = { showFullScreenImage = false }
        )
    }

}
@Composable
fun TestPinchToZoomView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray) // for visual reference
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = "https://firebasestorage.googleapis.com/v0/b/xspand-db058.firebasestorage.app/o/00000506_017.png?alt=media&token=99d68440-cfb4-42dc-986e-a3c810c84af8" // test URL
            ),
            contentDescription = "Test Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}
@Composable
fun FullScreenImageDialog(
    imageUrl: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            PinchToZoomView(
                modifier = Modifier.fillMaxSize(),
                imageContentDescription = "X-Ray Image",
                imageUrl = imageUrl
            )
            // Close icon in top-left corner
            IconButton(
                onClick = onDismiss,
                modifier = Modifier
                    .align(Alignment.TopStart).size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}

@Composable
fun PinchToZoomView(
    modifier: Modifier,
    imageContentDescription: String = "",
    imageUrl: String
) {
    // Mutable state variables to hold scale and offset values
    var scale by remember { mutableStateOf(1f) }
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    val minScale = 1f
    val maxScale = 4f

    // Remember the initial offset
    var initialOffset by remember { mutableStateOf(Offset(0f, 0f)) }

    // Coefficient for slowing down movement
    val slowMovement = 0.5f

    // Box composable containing the image
    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    // Update scale with the zoom
                    val newScale = scale * zoom
                    scale = newScale.coerceIn(minScale, maxScale)

                    // Calculate new offsets based on zoom and pan
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    val offsetXChange = (centerX - offsetX) * (newScale / scale - 1)
                    val offsetYChange = (centerY - offsetY) * (newScale / scale - 1)

                    // Calculate min and max offsets
                    val maxOffsetX = (size.width / 2) * (scale - 1)
                    val minOffsetX = -maxOffsetX
                    val maxOffsetY = (size.height / 2) * (scale - 1)
                    val minOffsetY = -maxOffsetY

                    // Update offsets while ensuring they stay within bounds
                    if (scale * zoom <= maxScale) {
                        offsetX = (offsetX + pan.x * scale * slowMovement + offsetXChange)
                            .coerceIn(minOffsetX, maxOffsetX)
                        offsetY = (offsetY + pan.y * scale * slowMovement + offsetYChange)
                            .coerceIn(minOffsetY, maxOffsetY)
                    }
                    if (pan != Offset(0f, 0f) && initialOffset == Offset(0f, 0f)) {
                        initialOffset = Offset(offsetX, offsetY)
                    }

                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        // Reset scale and offset on double tap
                        if (scale != 1f) {
                            scale = 1f
                            offsetX = initialOffset.x
                            offsetY = initialOffset.y
                        } else {
                            scale = 2f
                        }
                    }
                )
            }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                translationX = offsetX
                translationY = offsetY
            }
    ) {
        // Image to be displayed with pinch-to-zoom functionality
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl
            ),
            contentDescription = imageContentDescription,
            modifier = Modifier.fillMaxSize(),
        )
    }
}


