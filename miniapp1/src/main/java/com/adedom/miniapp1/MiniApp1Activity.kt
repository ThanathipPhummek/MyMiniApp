package com.adedom.miniapp1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.adedom.core.MiniAppProtocol
import com.adedom.miniapp1.ui.theme.MyMiniAppTheme
import org.koin.android.ext.android.inject

internal class MiniApp1Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.data
        val send = data?.getQueryParameter("send")
        val protocol: MiniAppProtocol by inject()

        protocol.saveLogListener("MiniApp1")

        setContent {
            MyMiniAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(modifier = Modifier.fillMaxSize(),painter = painterResource(id = R.drawable.goodtime), contentDescription = "")
                        Column {
//                            Text(text = "จำนวนการกด ${protocol.logCount}")
                            Button(onClick = {
                                val intent = Intent()
                                intent.putExtra("receive", "GOODTIME RADIO")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
//                            protocol.listener?.invoke("Back MiniApp1")
                                protocol.saveLogListener("Back MiniApp1")
                            }) {
                                Text(text = protocol.getText())
                            }
                        }
                    }
                }
            }
        }
    }
    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, MiniApp1Activity::class.java))
        }
    }
}