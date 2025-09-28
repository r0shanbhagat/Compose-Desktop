package com.myapp.ui.feature.notification

import com.myapp.data.repo.MyRepo
import com.myapp.util.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class NotificationViewModel @Inject constructor(
    private val myRepo: MyRepo,
) : ViewModel() {
    companion object {
        const val INIT_WELCOME_MSG = "Hello World!"
    }

    private val _welcomeText = MutableStateFlow(INIT_WELCOME_MSG)
    val welcomeText: StateFlow<String> = _welcomeText

    fun onClickMeClicked() {
        _welcomeText.value = myRepo.getClickedWelcomeText()
    }


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