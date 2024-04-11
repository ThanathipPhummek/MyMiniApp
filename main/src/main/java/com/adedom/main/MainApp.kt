package com.adedom.main

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.adedom.miniapp1.MiniApp1Adapter
import com.adedom.miniapp1.MiniApp1Bundle
import com.adedom.miniapp1.MiniApp1Protocol
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
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.align(Alignment.Center)
        ) {
            miniApp1Protocol.MiniApp1Icon(
                modifier = Modifier.clickable {
                    miniApp1Protocol.open(context, MiniApp1Bundle("hello world"))
                }
            )
            miniApp2Protocol.MiniApp2Icon(
                modifier = Modifier.clickable {
                    miniApp2Protocol.open(context, MiniApp2Bundle("สวัสดีชาวโลก"))
                }
            )
        }
    }
}
