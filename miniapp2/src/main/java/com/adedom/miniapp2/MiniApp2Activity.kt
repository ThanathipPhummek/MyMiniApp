package com.adedom.miniapp2

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
import com.adedom.miniapp2.ui.theme.MyMiniAppTheme

internal class MiniApp2Activity : ComponentActivity() {

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
                    Column {
                        Greeting("Android : $send")
                        Button(onClick = {
                            Intent().apply {
                                putExtra("receive", "Ch7HD")
                                setResult(Activity.RESULT_OK, this)
                                finish()
                            }
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