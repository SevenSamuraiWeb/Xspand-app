package com.example.xspand_app.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xspand_app.Header

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageDoctor(){
    var searchValue by remember { mutableStateOf("") }

    Column(modifier=Modifier.fillMaxSize().padding(8.dp)){
        Header("Home page", Color(0xFF48A6A7),modifier=Modifier.padding(top = 16.dp))
        Row(modifier=Modifier.fillMaxWidth()){
            SearchBar(
                query = searchValue,
                onQueryChange = { searchValue = it },
                active = false,
                onActiveChange = {},
                placeholder = { Text("Search") },
                modifier = Modifier.fillMaxWidth(),
                onSearch = {}
            ){}
            Spacer(modifier=Modifier.height(16.dp))

        }
    }
}



@Composable
fun ScrollableItem(){

}


@Preview(showBackground = true)
@Composable
fun HomePageDoctorPreview(){
    HomePageDoctor()
}