package com.myapp.ui.feature.passcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.myapp.di.AppComponent
import com.myapp.ui.component.ThemedOutlinedTextField
import com.myapp.ui.value.R

class PasscodeScreen(
    val appComponent: AppComponent,
) : Screen {
    init {
        appComponent.inject(this)
    }

    @Composable
    override fun Content() {
        PasscodeScreenContent()
    }
}

@Composable
fun PasscodeScreenContent() {
    var version by remember { mutableStateOf("") }
    val passcode = getPasscodeForVersion(version)

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(100.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                elevation = 8.dp,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Enter Version",
                        style = MaterialTheme.typography.h4,
                        color = R.color.Primay,
                        modifier = Modifier.padding(bottom = 20.dp),
                        textAlign = TextAlign.Center
                    )

                    ThemedOutlinedTextField(
                        value = version,
                        onValueChange = { version = it },
                        label = { Text("Version") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(28.dp))
                    if (passcode.isNotEmpty()) {
                        Text(
                            "Passcode: $passcode",
                            fontSize = 18.sp,
                            color = if (passcode.startsWith("PASS")) Color(0xFF2E7D32) else Color(
                                0xFFD32F2F
                            )
                        )
                    }
                }
            }
        }
    }
}


fun getPasscodeForVersion(version: String): String {
    // Replace this logic with your real version/passcode mappings
    return when (version.trim()) {
        "1.0" -> "PASS-1001"
        "2.0" -> "PASS-2002"
        "3.0" -> "PASS-3003"
        else -> if (version.isNotBlank()) "Unknown version" else ""
    }
}
