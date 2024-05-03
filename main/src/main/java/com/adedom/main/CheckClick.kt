package com.adedom.main

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.adedom.core.MiniAppProtocol
import org.koin.java.KoinJavaComponent

fun checkClick(deeplink: String, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
    val protocol: MiniAppProtocol by KoinJavaComponent.inject(MiniAppProtocol::class.java)
    val prevMessage: String? = null
    while (true) {
        val newMessage = protocol.message
        if (newMessage != prevMessage) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(deeplink))
            launcher.launch(intent)
            protocol.sendMessage("")
            break
        }
    }
}