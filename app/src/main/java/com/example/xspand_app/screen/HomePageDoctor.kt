package com.example.xspand_app.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xspand_app.Header

@Composable
fun HomePageDoctor(){
    Column(modifier=Modifier.fillMaxSize().padding(8.dp)){
        Header("Home page", Color(0xFF48A6A7))
        Row(modifier=Modifier.fillMaxWidth()){
            OutlinedTextField(modifier=Modifier.clip(RoundedCornerShape(32.dp))){
                TextFieldValue ="",
                onValueChange = {},
            }
        }
    }
}

@Preview
@Composable
fun HomePageDoctorPreview(){
    HomePageDoctor()
}