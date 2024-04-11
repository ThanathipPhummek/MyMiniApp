package com.adedom.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainApp() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Button(onClick = {

            }) {
                Text(text = "Mini app 1")
            }
            Button(onClick = {

            }) {
                Text(text = "Mini app 1")
            }
        }
    }
}
