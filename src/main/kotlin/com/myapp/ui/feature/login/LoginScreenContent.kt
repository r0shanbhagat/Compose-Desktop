package com.myapp.ui.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myapp.ui.component.ThemedOutlinedTextField
import com.myapp.ui.value.R

@Composable
fun LoginScreenContent(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    // Login button enabled only if both fields have content
    val isLoginEnabled = email.isNotBlank() && password.isNotBlank()

    // Gradient background colors
    //val gradientColors = listOf(Color(0xFF6A11CB), Color(0xFF2575FC))

    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colors.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .background(Color.White.copy(alpha = 0.95f), shape = RoundedCornerShape(20.dp))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Welcome Back!",
                    style = MaterialTheme.typography.h4,
                    color = R.color.Primay,
                    modifier = Modifier.padding(bottom = 20.dp),
                    textAlign = TextAlign.Center
                )

                // Email input
                ThemedOutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = it.isBlank()
                    },
                    isError = emailError,
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                if (emailError) {
                    Text(
                        "Email cannot be empty",
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp, start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Password input
                ThemedOutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = it.isBlank()
                    },
                    isError = passwordError,
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                )
                if (passwordError) {
                    Text(
                        "Password cannot be empty",
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp, start = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        // Check for empty fields again and login
                        emailError = email.isBlank()
                        passwordError = password.isBlank()
                        if (!emailError && !passwordError) {
                            onLoginSuccess()
                        }
                    },
                    enabled = isLoginEnabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (isLoginEnabled) R.color.Primay else Color.LightGray,
                        contentColor = Color.White
                    )
                ) {
                    Text("Login", fontSize = 16.sp)
                }
            }
        }
    }
}


