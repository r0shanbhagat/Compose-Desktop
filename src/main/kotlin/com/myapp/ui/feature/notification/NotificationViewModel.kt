package com.myapp.ui.feature.notification

import com.myapp.util.ViewModel
import kotlinx.coroutines.delay

class NotificationViewModel : ViewModel() {


    suspend fun sendNotification(
        token: String,
        title: String,
        body: String,
        // serverKey: String
    ): Result<String> {
        delay(2000)
//        val client = HttpClient() // Configure as needed
//        return try {
//            val response = client.post("https://fcm.googleapis.com/fcm/send") {
//                header(HttpHeaders.Authorization, "key=$serverKey")
//                contentType(ContentType.Application.Json)
//                setBody("""
//                {
//                  "to": "$token",
//                  "notification": {
//                    "title": "$title",
//                    "body": "$body"
//                  }
//                }
//            """.trimIndent())
//            }
//            if (response.status.isSuccess()) {
//                Result.success("FCM sent")
//            } else {
//                Result.failure(Exception("FCM error: ${response.status}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(Exception("Network or server error: ${e.localizedMessage}"))
//        } finally {
//            client.close()
//        }
        return Result.failure(Exception("Network or server error: "))
    }

}