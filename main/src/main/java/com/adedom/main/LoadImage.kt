package com.adedom.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.SubcomposeAsyncImage

@Composable
fun LoadImage(url: String,modifier: Modifier) {
    SubcomposeAsyncImage(
        loading = {
            Box(
                modifier = modifier)
        },
        model = url,
        contentDescription = "news image",
        modifier = Modifier
            .fillMaxSize()
    )
    if (url.isNullOrEmpty()) {

    }
}
