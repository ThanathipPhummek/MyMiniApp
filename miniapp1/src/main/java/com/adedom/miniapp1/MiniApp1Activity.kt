package com.adedom.miniapp1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.adedom.core.MiniAppProtocol
import com.adedom.miniapp1.ui.theme.MyMiniAppTheme
import org.koin.compose.koinInject

internal class MiniApp1Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.data
        val send = data?.getQueryParameter("send")

        setContent {
            MyMiniAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val protocol = koinInject<MiniAppProtocol>()
                    Column {
                        Greeting("Android : $send")
                        Greeting("Android : ${protocol.message}")
                        Button(onClick = {
                            val intent = Intent()
                            intent.putExtra("receive", "BBTV")
                            setResult(Activity.RESULT_OK, intent)
                            finish()
                            protocol.listener?.invoke("BBTV2")
                        }) {
                            Text(text = "Back")
                        }
                    }
                }
            }
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