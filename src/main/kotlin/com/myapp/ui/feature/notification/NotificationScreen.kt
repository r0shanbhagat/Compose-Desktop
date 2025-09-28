package com.myapp.ui.feature.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
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
import com.myapp.ui.component.ThemedOutlinedTextField
import com.myapp.ui.value.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Model for a notification (received or sent)
data class NotificationMessage(val title: String, val body: String, val time: String)

@Composable
fun NotificationScreen(
    notifications: List<NotificationMessage>,
    onSendNotification: suspend (title: String, body: String, token: String) -> Result<String>
) {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var alertType by remember { mutableStateOf("") }
    var alertCode by remember { mutableStateOf("") }
    var token by remember { mutableStateOf("") }
    var sending by remember { mutableStateOf(false) }
    var successMsg by remember { mutableStateOf<String?>(null) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
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
                    "Notification Panel",
                    style = MaterialTheme.typography.h4,
                    color = R.color.Primay,
                    modifier = Modifier.padding(bottom = 20.dp),
                    textAlign = TextAlign.Center
                )

                ThemedOutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                ThemedOutlinedTextField(
                    value = body, onValueChange = { body = it },
                    label = { Text("Body") }, modifier = Modifier.fillMaxWidth()
                )
                ThemedOutlinedTextField(
                    value = body, onValueChange = { alertType = it },
                    label = { Text("Alert Type") }, modifier = Modifier.fillMaxWidth()
                )
                ThemedOutlinedTextField(
                    value = body, onValueChange = { alertCode = it },
                    label = { Text("Alert Code") }, modifier = Modifier.fillMaxWidth()
                )
                ThemedOutlinedTextField(
                    value = token, onValueChange = { token = it },
                    label = { Text("Device Token") }, modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = {
                        // Validate before sending
                        errorMsg = null
                        successMsg = null
                        sending = true
                        // Suspend function for sending, needs to be called in coroutine scope
                        GlobalScope.launch {
                            val result = onSendNotification(title, body, token)
                            sending = false
                            result.onSuccess {
                                successMsg = "Notification sent!"
                                // Reset fields if desired
                                title = ""; body = ""; token = ""
                            }.onFailure { error ->
                                errorMsg = error.message ?: "Unknown error sending notification"
                            }
                        }
                    },
                    enabled = !sending && title.isNotBlank() && body.isNotBlank() && alertType.isNotBlank() && alertCode.isNotBlank() && token.isNotBlank(),
                    modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (!sending && title.isNotBlank() && body.isNotBlank() && token.isNotBlank()) R.color.Primay else Color.LightGray,
                        contentColor = Color.White
                    )
                ) {
                    if (sending) CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        strokeWidth = 2.dp
                    )
                    else Text("Send  Notification", fontSize = 16.sp)
                }
                if (successMsg != null) {
                    Text(
                        successMsg!!,
                        color = Color(0xFF2E7D32),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                if (errorMsg != null) {
                    Text(
                        errorMsg!!,
                        color = Color(0xFFD32F2F),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        Text("Recent Notifications", fontSize = 18.sp, color = Color.White)
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f)
                .background(Color.White, MaterialTheme.shapes.medium)
        ) {
            items(notifications.size) { idx ->
                val n = notifications[idx]
                Column(Modifier.padding(16.dp)) {
                    Text(n.title, fontSize = 16.sp, color = Color(0xFF333333))
                    Text(n.body, fontSize = 14.sp, color = Color(0xFF666666))
                    Text(n.time, fontSize = 12.sp, color = Color(0xFFAAAAAA))
                }
                if (idx < notifications.lastIndex)
                    Divider(Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}
