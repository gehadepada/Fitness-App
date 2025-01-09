package com.example.fitnessapp.ui.screens.signup_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessapp.ui.screens.signup_screen.components.CustomOutlinedTextField
import com.example.fitnessapp.ui.theme.FitnessAppTheme

@Composable
fun SignUpScreen(onSignUp: (String, String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Error states
    var usernameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sign Up",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Username Field
        CustomOutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                if (it.isNotBlank()) usernameError = ""
            },
            label = "Username",
            errorMessage = usernameError
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Email Field
        CustomOutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                if (it.contains("@") && it.contains(".")) emailError = ""
            },
            label = "Email",
            errorMessage = emailError
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Password Field
        CustomOutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                if (it.length >= 6) passwordError = ""
            },
            label = "Password",
            isPassword = true,
            errorMessage = passwordError
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {
                // Validation
                usernameError = if (username.isBlank()) "Username cannot be empty" else ""
                emailError = if (!email.contains("@") || !email.contains(".")) "Invalid email format" else ""
                passwordError = if (password.length < 6) "Password must be at least 6 characters" else ""

                // If no errors, proceed with signup
                if (usernameError.isEmpty() && emailError.isEmpty() && passwordError.isEmpty()) {
                    onSignUp(username, email, password)
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
        ) {
            Text(text = "Get Started", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Already have an account?",
                fontSize = 15.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 11.dp)
            )
            TextButton(onClick = {}) {
                Text(
                    text = "Login",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Green,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Prev() {
    FitnessAppTheme {
        SignUpScreen(
            onSignUp = { _, _, _ -> }
        )
    }
}