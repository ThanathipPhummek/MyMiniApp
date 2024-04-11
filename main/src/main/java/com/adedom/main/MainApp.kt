package com.adedom.main

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.adedom.miniapp1.MiniApp1Activity
import com.adedom.miniapp1.MiniApp1Adapter
import com.adedom.miniapp1.MiniApp1Bundle
import com.adedom.miniapp1.MiniApp1Protocol
import com.adedom.miniapp2.MiniApp2Activity
import com.adedom.miniapp2.MiniApp2Adapter
import com.adedom.miniapp2.MiniApp2Bundle
import com.adedom.miniapp2.MiniApp2Protocol

@Composable
fun MainApp() {
    val context = LocalContext.current
    val miniApp1Protocol: MiniApp1Protocol = MiniApp1Adapter(
        listener = { bundle ->
            Toast.makeText(context, bundle.message, Toast.LENGTH_SHORT).show()
        }
    )
    val miniApp2Protocol: MiniApp2Protocol = MiniApp2Adapter(
        listener = { bundle ->
            Toast.makeText(context, bundle.message, Toast.LENGTH_SHORT).show()
        }
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Button(onClick = {
                MiniApp1Activity.open(
                    context = context,
                    bundle = MiniApp1Bundle("hello world"),
                    protocol = miniApp1Protocol,
                )
            }) {
                Text(text = "Mini app 1")
            }
            Button(onClick = {
                MiniApp2Activity.open(
                    context = context,
                    bundle = MiniApp2Bundle("สวัสดีชาวโลก"),
                    protocol = miniApp2Protocol,
                )
            }) {
                Text(text = "Mini app 2")
            }
        }
    }
}
