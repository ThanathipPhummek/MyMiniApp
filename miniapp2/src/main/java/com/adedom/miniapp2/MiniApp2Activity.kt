package com.adedom.miniapp2

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
import com.adedom.miniapp2.ui.theme.MyMiniAppTheme

internal class MiniApp2Activity : ComponentActivity() {

    private var bundle: MiniApp2Bundle? = null

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
                            protocol?.listener?.invoke(MiniApp2Bundle("Ch7HD"))
                            protocol?.close(this@MiniApp2Activity)
                        }) {
                            Text(text = "Back")
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val BUNDLE_KEY = "mini-app-2"
        private var protocol: MiniApp2Protocol? = null

        fun open(
            context: Context,
            bundle: MiniApp2Bundle,
            protocol: MiniApp2Protocol,
        ) {
            val intent = Intent(context, MiniApp2Activity::class.java)
            intent.putExtra(BUNDLE_KEY, bundle)
            context.startActivity(intent)
            this.protocol = protocol
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