package com.example.xspand_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.rememberAsyncImagePainter
import com.example.xspand_app.data.Patient

@Composable
fun ScrollableColumn(items:List<Patient>,modifier: Modifier){
    LazyColumn(modifier) {

    }
}

@Composable
fun ScrollableItem(item:Patient){
    Row(modifier=Modifier.fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.Center
    ) {

    }
}