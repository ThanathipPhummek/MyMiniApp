package com.adedom.miniapp3

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
import com.adedom.miniapp3.ui.theme.MyMiniAppTheme
import org.koin.android.ext.android.inject

internal class MiniApp3Activity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.data
        val send = data?.getQueryParameter("send")

        val protocol: MiniAppProtocol by inject()
        protocol.saveLogListener("MiniApp3")

        setContent {
            MyMiniAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(modifier = Modifier.fillMaxSize(),painter = painterResource(id = R.drawable.bugaboo), contentDescription = "" )
                        Column {
                            Button(onClick = {
                                val intent = Intent()
                                intent.putExtra("receive", "BUGABOO")
                                setResult(RESULT_OK, intent)
                                finish()
                                protocol.saveLogListener("Back MiniApp3")
                            }) {
                                Text(text = "${protocol.text}")
                            }
                        }
                    }
                }
            }
        }
    }
    companion object {
        fun open(context: Context) {
            context.startActivity(Intent(context, MiniApp3Activity::class.java))
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