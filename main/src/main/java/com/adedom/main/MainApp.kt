package com.adedom.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adedom.core.DefaultValue
import com.adedom.core.MiniAppProtocol
import org.koin.compose.koinInject
import org.koin.java.KoinJavaComponent

@Composable
fun MainApp() {
    val context = LocalContext.current
    val protocol = koinInject<MiniAppProtocol>()

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
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    painter = painterResource(id = R.drawable.header),
                    contentDescription = ""
                )
            }
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(235.dp),
                    painter = painterResource(id = R.drawable.player),
                    contentDescription = ""
                )
            }
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    painter = painterResource(id = R.drawable.banner),
                    contentDescription = ""
                )
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp)
                ) {
                    item.forEach { defaultValue ->
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        Column(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(8.dp))
                                .background(Color.LightGray)
                                .clickable {
                                    protocol.sendMessage(defaultValue.appPath ?: "")
                                    defaultValue.deeplink?.let { checkClick(it, launcher) }
                                }
                                .padding(8.dp)
                        ) {
                            Box(
                                Modifier
                                    .height(100.dp)
                                    .width(90.dp)
                            ) {
                                LoadImage(
                                    url = defaultValue.icon ?: "", modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                            Text(
                                text = defaultValue.appName ?: "Default App Name",
                                modifier = Modifier.width(90.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp),
                    painter = painterResource(id = R.drawable.showroom),
                    contentDescription = ""
                )
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
                .width(80.dp)
                .align(Alignment.TopEnd)
                .padding(bottom = 16.dp, end = 8.dp)
                .background(color = Color.White.copy(alpha = 0.0f))
        ) {
        }
    }
}

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


//            Button(
//                onClick = {
//                    protocol.sendMessage(defaultValue.deeplink?:"")
//                    try {
//                        defaultValue.appName?.let { appName ->
//                            val intent = Intent(
//                                Intent.ACTION_VIEW,
//                                Uri.parse(defaultValue.deeplink),
//                            )
//                            launcher.launch(intent)
//                        }
//                    } catch (e:Exception) {
//                        Toast.makeText(context, "something wrong", Toast.LENGTH_SHORT).show()
//                    }
//                },
//                modifier = Modifier.padding(8.dp)
//            ) {
//                if (!defaultValue.icon.isNullOrBlank()) {
//                    Box(
//                        Modifier
//                            .height(40.dp)
//                            .width(40.dp)
//                            .padding(4.dp)
//                    ) {
//                        LoadImage(
//                            url = defaultValue.icon ?: "", modifier = Modifier
//                                .fillMaxSize()
//                        )
//                    }
//                }
//                Text(defaultValue.appName ?: "Default App Name")
//            }