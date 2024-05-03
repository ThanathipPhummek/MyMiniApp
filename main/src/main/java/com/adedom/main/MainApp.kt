package com.adedom.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adedom.core.DefaultValue
import com.adedom.core.MainAppProtocol
import com.adedom.core.MiniAppProtocol
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.koin.compose.koinInject

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainApp() {
    val context = LocalContext.current
    val protocol = koinInject<MiniAppProtocol>()

    val item: List<DefaultValue> = protocol.listDefault
    protocol.saveLogListener("MainApp")

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val receive = intent?.getStringExtra("receive")
                Toast.makeText(context, receive, Toast.LENGTH_SHORT).show()
            }
        }

    val pagerState = rememberPagerState(initialPage = 0)
    var text by remember { mutableStateOf(TextFieldValue("")) }

    val brush = Brush.horizontalGradient(
        colors = listOf(Color(0xFFc016c8), Color(0xFFe577ea))
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    painter = painterResource(id = R.drawable.header),
                    contentDescription = ""
                )
            }
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(235.dp),
                    painter = painterResource(id = R.drawable.player),
                    contentDescription = ""
                )
            }
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    painter = painterResource(id = R.drawable.banner),
                    contentDescription = ""
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .height(320.dp)
                        .background(brush)
                ) {
                    Text(
                        text = "สำหรับตุณ",
                        Modifier
                            .padding(top = 18.dp)
                            .padding(start = 38.dp),
                        fontSize = 10.sp,
                        color = Color.White
                    )
                    Text(
                        text = "แอปดีประจำเดือน",
                        Modifier
                            .padding(bottom = 28.dp)
                            .padding(start = 38.dp),
                        fontSize = 24.sp,
                        color = Color.White
                    )
                    HorizontalPager(
                        count = (item.size / 2) + (if (item.size % 2 == 0) 0 else 1),
                        state = pagerState,
                        modifier = Modifier
                    ) { page ->
                        Column(modifier = Modifier.fillMaxHeight()) {
                            item.subList(page * 2, minOf((page * 2) + 2, item.size))
                                .forEach { defaultValue ->
                                    Row(Modifier.fillMaxWidth()) {
                                        Spacer(modifier = Modifier.padding(16.dp))
                                        Box(
                                            Modifier
                                                .height(90.dp)
                                                .width(90.dp)
                                                .padding(8.dp)
                                                .clickable {
                                                    protocol.sendMessage(defaultValue.appPath ?: "")
                                                    protocol.sendText(text.text)
                                                    defaultValue.deeplink?.let {
                                                        checkClick(
                                                            it,
                                                            launcher
                                                        )
                                                    }
                                                }
                                                .clip(shape = RoundedCornerShape(16.dp))
                                        ) {
                                            LoadImage(
                                                url = defaultValue.icon ?: "",
                                                modifier = Modifier.fillMaxSize()
                                            )
                                        }
                                        Column {
                                            Text(
                                                text = defaultValue.appName ?: "Default App Name",
                                                fontSize = 18.sp,
                                                modifier = Modifier.padding(
                                                    start = 16.dp,
                                                    top = 14.dp
                                                ),
                                                color = Color.White
                                            )
                                            Text(
                                                text = defaultValue.appPath ?: "Default App Name",
                                                fontSize = 12.sp,
                                                modifier = Modifier.padding(
                                                    start = 16.dp,
                                                    top = 4.dp
                                                ),
                                                color = Color.White
                                            )
                                        }
                                    }
                                }
                        }
                    }
                }
                OutlinedTextField(
                    value = text,
                    label = { Text(text = "Enter Your Text") },
                    onValueChange = { newText ->
                        text = newText
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                )
            }

            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp),
                    painter = painterResource(id = R.drawable.showroom),
                    contentDescription = ""
                )
            }
        }
        Button(
            onClick = {
                try {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("bugaboo://app-log?send=APPLOG"),
                    )
                    launcher.launch(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "something wrong", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .width(80.dp)
                .align(Alignment.TopEnd)
                .padding(bottom = 16.dp, end = 8.dp)
                .background(color = Color.White.copy(alpha = 0.0f))
        ) {
        }
    }
}