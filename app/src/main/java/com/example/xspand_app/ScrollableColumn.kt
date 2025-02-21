package com.example.xspand_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.example.xspand_app.data.Person

@Composable
fun ScrollableColumn(items:List<Person>,modifier: Modifier){


    LazyColumn(modifier) {

    }
}

@Composable
fun ScrollableItem(item:Person){
    Row(modifier=Modifier.fillMaxWidth(0.8f),
        horizontalArrangement = Arrangement.Center
    ) {
        val paint:Painter = rememberAsyncImagePainter(item.image)
        Image(painter =())
    }
}