package com.adedom.main

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.adedom.core.MainAppProtocol
import org.koin.java.KoinJavaComponent

fun checkClick(deeplink: String, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
    val protocol: MainAppProtocol by KoinJavaComponent.inject(MainAppProtocol::class.java)
    val prevMessage: String? = null
    while (true) {
        val newMessage = protocol.message
        if (newMessage != prevMessage) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(deeplink))
                launcher.launch(intent)
                protocol.sendMessage("")
                break
            } catch (e: Exception) {
                Log.d("maxmax", "checkClick: $e")
            }
        }
    }
}