package com.example.applog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adedom.core.MiniAppProtocol
import com.example.applog.ui.theme.MyMiniAppTheme
import org.koin.android.ext.android.inject

internal class AppLogActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.data
        val send = data?.getQueryParameter("send")

        val protocol: MiniAppProtocol by inject()

        protocol.saveLogListener("Log Screen")

        val item : List<String> = protocol.saveLog

        setContent {
            MyMiniAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn {
                            items(item) { log ->
                                Text(text = log, modifier = Modifier.padding(8.dp))
                            }
                        }
                        Button(onClick = {
                            val intent = Intent()
                            intent.putExtra("receive", "Ch7HD")
                            setResult(Activity.RESULT_OK, intent)
                            finish()
                            protocol.saveLogListener("back Log Screen")
                        }, modifier = Modifier.width(300.dp).align(Alignment.BottomCenter).padding(16.dp)) {
                            Text(text = "Back")
                        }
                    }
                }
            }
        }
    }
}