package com.example.e_commerce.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_commerce.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.e_commerce.AppUtil
import com.example.e_commerce.viewmodel.AuthViewModel
import java.nio.file.WatchEvent

@Composable
fun LoginScreen(modifier: Modifier = Modifier,navController: NavController,authViewModel: AuthViewModel = viewModel()) {

    var email by remember() {
        mutableStateOf("")
    }
    var password by remember() {
        mutableStateOf("")
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello there!",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Welcome Back",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 22.sp
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email address")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))


        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = {
                isLoading = true
                authViewModel.logIn(email,password){success,message->
                    if(success){
                        isLoading = false
                        navController.navigate("home"){
                            popUpTo("auth"){
                                inclusive = true
                            }
                        }
                    }else{
                        isLoading = false
                        AppUtil.showToast(context,message?:"Unknown Error")
                    }
                }

            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth().height(60.dp)
        ) {
            Text(
                text = if (isLoading)"Logging in" else "Login",
                fontSize = 22.sp
            )
        }
    }
}