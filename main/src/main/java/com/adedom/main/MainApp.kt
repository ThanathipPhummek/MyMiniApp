package com.adedom.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.adedom.core.DefaultValue
import com.adedom.core.MiniAppProtocol
import org.koin.compose.koinInject
import org.koin.core.qualifier.named

@Composable
fun MainApp() {
    val context = LocalContext.current
    val protocol = koinInject<MiniAppProtocol>()
    protocol.setOnClickListener { message ->
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    var item : List<DefaultValue> = protocol.listDefault

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val receive = intent?.getStringExtra("receive")
                Toast.makeText(context, receive, Toast.LENGTH_SHORT).show()
            }
        }

    Column(modifier = Modifier.fillMaxSize()) {
        item.forEach { defaultValue ->
            Button(
                onClick = {
                    defaultValue.appName?.let { appName ->
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(defaultValue.deeplink),
                        )
                        launcher.launch(intent)
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                if (!defaultValue.icon.isNullOrBlank()) {
                    Box(
                        Modifier
                            .height(40.dp)
                            .width(40.dp).padding(4.dp)) {
                        LoadImage(url = defaultValue.icon?:"", modifier = Modifier
                            .fillMaxSize())
                    }
                }
                Text(defaultValue.appName ?: "Default App Name")
            }
        }
    }

//    Box(modifier = Modifier.fillMaxSize()) {
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier.align(Alignment.Center)
//        ) {
//            Button(onClick = {
//                val intent = Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("bugaboo://mini-app-1?send=hello world"),
//                )
//                launcher.launch(intent)
//                protocol.message = "hello world2"
//            }) {
//                Text(text = "Mini app 1")
//            }
//
//            Button(onClick = {
//                val intent = Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("bugaboo://mini-app-2?send=สวัสดีชาวโลก"),
//                )
//                launcher.launch(intent)
//                protocol.message = "สวัสดีชาวโลก2"
//            }) {
//                if (icon.isNotBlank()) {
//                    Box(
//                        Modifier
//                            .height(40.dp)
//                            .width(40.dp).padding(4.dp)) {
//                        LoadImage(url = icon, modifier = Modifier
//                            .fillMaxSize())
//                    }
//                }
//                Text(text = appName)
//            }
//        }
//    }
}
