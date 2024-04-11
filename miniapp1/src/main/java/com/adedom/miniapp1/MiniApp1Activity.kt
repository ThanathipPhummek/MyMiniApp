package com.adedom.miniapp1

import android.content.Context
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
import com.adedom.miniapp1.ui.theme.MyMiniAppTheme

class MiniApp1Activity : ComponentActivity() {

    private var bundle: MiniApp1Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bundle = intent.getParcelableExtra(BUNDLE_KEY)

        setContent {
            MyMiniAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Greeting("Android : ${bundle?.message}")
                        Button(onClick = {
                            protocol?.listener?.invoke(MiniApp1Bundle("BBTV"))
                            finish()
                        }) {
                            Text(text = "Back")
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val BUNDLE_KEY = "mini-app-1"
        private var protocol: MiniApp1Protocol? = null

        fun open(
            context: Context,
            bundle: MiniApp1Bundle,
            protocol: MiniApp1Protocol,
        ) {
            val intent = Intent(context, MiniApp1Activity::class.java)
            intent.putExtra(BUNDLE_KEY, bundle)
            context.startActivity(intent)
            this.protocol = protocol
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyMiniAppTheme {
        Greeting("Android")
    }
}