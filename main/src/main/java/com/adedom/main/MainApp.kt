package com.adedom.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.adedom.core.DefaultValue
import com.adedom.core.MiniAppProtocol
import org.koin.compose.koinInject

@Composable
fun MainApp() {
    val context = LocalContext.current
    val protocol = koinInject<MiniAppProtocol>()
//    protocol.setOnClickListener { message ->
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//    }
    val item: List<DefaultValue> = protocol.listDefault
    protocol.saveLogListener("MainApp")

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val receive = intent?.getStringExtra("receive")
//                Toast.makeText(context, receive, Toast.LENGTH_SHORT).show()
            }
        }

    Box(modifier = Modifier.fillMaxSize()) {
    Column(modifier = Modifier.fillMaxSize()) {
        item.forEach { defaultValue ->
            Button(
                onClick = {
                    try {
                        defaultValue.appName?.let { appName ->
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(defaultValue.deeplink),
                            )
                            launcher.launch(intent)
                        }
                    } catch (e:Exception) {
                        Toast.makeText(context, "something wrong", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                if (!defaultValue.icon.isNullOrBlank()) {
                    Box(
                        Modifier
                            .height(40.dp)
                            .width(40.dp)
                            .padding(4.dp)
                    ) {
                        LoadImage(
                            url = defaultValue.icon ?: "", modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }
                Text(defaultValue.appName ?: "Default App Name")
            }
        }
    }
        Button(
            onClick = {
                try {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("bugaboo://app-log?send=APPLOG"),
                    )
                    launcher.launch(intent)
                } catch (e:Exception) {
                    Toast.makeText(context, "something wrong", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Text("LOG Screen")
        }
    }
}
