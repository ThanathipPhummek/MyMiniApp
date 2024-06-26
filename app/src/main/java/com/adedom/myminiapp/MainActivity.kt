package com.adedom.myminiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.adedom.core.MiniAppProtocol
import com.adedom.main.MainApp
import com.adedom.miniapp1.defaultValue1
import com.adedom.miniapp2.defaultValue2
import com.adedom.miniapp3.defaultValue3
import com.adedom.myminiapp.ui.theme.MyMiniAppTheme
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val protocol: MiniAppProtocol by inject(MiniAppProtocol::class.java)
        defaultValue1(protocol)
        defaultValue2(protocol)
        defaultValue3(protocol)
        setContent {
            MyMiniAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}