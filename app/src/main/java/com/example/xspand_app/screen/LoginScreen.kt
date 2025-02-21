package com.example.xspand_app.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xspand_app.R

@Composable
fun LoginScreen(modifier:Modifier = Modifier,navigateToHome:()->Unit){
    val defaultColor = Color(0xFFF2EFE7)
    val selectColor = Color(0xFF48A6A7)
    var selectText by remember { mutableStateOf<String?>(null) }
    var buttons = listOf("Admin","Doctor","Radiologist")
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var gradients = listOf(Color(0xFF317EBE),Color(0xFF9ACBD0))

    Column(
        modifier=Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(256.dp)
                .align(Alignment.End)
                .padding(bottom = 64.dp),
            painter = painterResource(R.drawable.ellipse_3),
            contentDescription = null
        )

        Row(modifier=Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            buttons.forEach{
                buttonText ->
                    Button(
                        onClick = {selectText = buttonText},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (buttonText == selectText) selectColor
                                else defaultColor)
                    ){
                        Text(
                            text=buttonText,
                            color=if (buttonText == selectText) Color.White else Color.Black
                        )
                    }
            }
        }
        Spacer(modifier=Modifier.height(20.dp))
        //login details
        Box(modifier = Modifier
            .fillMaxWidth(0.8f)
            .background(Brush.linearGradient(colors = gradients),shape=RoundedCornerShape(32.dp))
            .padding(32.dp)
                ){
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                OutlinedTextField(
                    singleLine = true,
                    modifier=Modifier.background(Color.White),
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text(text = "Username or email") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier=Modifier.background(Color.White).clip(RoundedCornerShape(32.dp)),
                    singleLine = true,
                    value = password,
                    onValueChange = { password = it },
                    placeholder = { Text(text = "Password")
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.clickable { /*navigate to forgot password screen*/ }
                        .align(Alignment.End).padding(end = 20.dp),
                    text = "Forgot password?",
                    color = Color(0xFF1414E6),
                    textDecoration = TextDecoration.Underline
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {navigateToHome()}) {
                    Text("Login")
                }
                    Spacer(modifier=Modifier.height(16.dp))
                Text(
                    modifier = Modifier.clickable { /*navigate to signup page*/ },
                    text="Don't have an account? Sign up",
                    color=Color(0xFF1414E6),
                    textDecoration = TextDecoration.Underline
                )
            }
        }
        Image(
            modifier = Modifier
                .size(256.dp)
                .align(Alignment.Start)
                .padding(top=30.dp),
            painter = painterResource(R.drawable.ellipse_4),
            contentDescription = null
        )




    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen{}
}