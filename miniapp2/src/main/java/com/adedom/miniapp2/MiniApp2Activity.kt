package com.adedom.miniapp2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import com.adedom.miniapp2.ui.theme.MyMiniAppTheme
import org.koin.android.ext.android.inject

internal class MiniApp2Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.data
        val send = data?.getQueryParameter("send")

        val protocol: MiniAppProtocol by inject()
        protocol.saveLogListener("MiniApp2")

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
                        Image(modifier = Modifier.fillMaxSize(),painter = painterResource(id = R.drawable.ch7hd), contentDescription = "" )
                        Column {
//                            Greeting("Android : $send")
//                        Greeting("Android : ${protocol.message}")
                            Button(onClick = {
                                val intent = Intent()
                                intent.putExtra("receive", "Ch7HD")
                                setResult(Activity.RESULT_OK, intent)
                                finish()
//                            protocol.listener?.invoke("Back MiniApp2")
                                protocol.saveLogListener("Back MiniApp2")
                            }) {
                                Text(text = "Back")
                            }
                        }
                    }
                }
            }
        }
    }
    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, MiniApp2Activity::class.java))
        }
    }
}

@Composable
internal fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
internal fun GreetingPreview() {
    MyMiniAppTheme {
        Greeting("Android")
    }
}