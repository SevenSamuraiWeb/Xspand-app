package com.example.xspand_app.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.size.Scale
import com.example.xspand_app.R
import com.example.xspand_app.data.XrayItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XRayDashboard(xRayItems: List<XrayItem>,onXrayItemClicked: (XrayItem) -> Unit = {}) {
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
                                text = "Dashboard",
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
    ) { paddingValues ->
        // Main content
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(xRayItems) { item ->
                XRayRow(item,onClick = { onXrayItemClicked(item) })
            }
        }
    }
}

@Composable
fun XRayRow(item: XrayItem, modifier: Modifier = Modifier,onClick:()->Unit = {}) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Image
        Image(
            painter = rememberAsyncImagePainter(
                model = item.imageUrl
            ),
            contentDescription = "X-ray image for ${item.patient.fullName}",
            modifier = Modifier
                .size(200.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop
        )

        // Text Info (Name, Age, Doctor)
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "${item.patient.fullName}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "${item.doctor.fullName}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Scan ID: ${item.scanId}",
                style = MaterialTheme.typography.bodySmall,
                fontStyle = FontStyle.Italic
            )
        }
    }
    HorizontalDivider()
}