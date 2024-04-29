package com.adedom.miniapp1

import android.content.Context
import android.util.Log
import com.adedom.core.DefaultValue
import com.adedom.core.MiniAppProtocol
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

@OptIn(DelicateCoroutinesApi::class)
fun defaultValue1(context: Context){
    val protocol: MiniAppProtocol by inject(MiniAppProtocol::class.java)
//    val newDefaultValue = DefaultValue(icon = "https://fastly.picsum.photos/id/16/2500/1667.jpg?hmac=uAkZwYc5phCRNFTrV_prJ_0rP0EdwJaZ4ctje2bY7aE", appName = "มินิแอพ 1", deeplink = "bugaboo://mini-app-1?send=MINIAPP1")
    val newDefaultValue = DefaultValue(
        icon = "https://fastly.picsum.photos/id/16/2500/1667.jpg?hmac=uAkZwYc5phCRNFTrV_prJ_0rP0EdwJaZ4ctje2bY7aE",
        appName = "มินิแอพ 1",
        deeplink = "miniapp1"
    )
    protocol.setDefaultCallback(newDefaultValue)

    GlobalScope.launch {
        var prevMessage = protocol.message
        while (true) {
            val newMessage = protocol.message
            if (newMessage != prevMessage) {
                Log.d("Counter", "MiniApp1: $newMessage")
                prevMessage = newMessage
                if (newMessage == "miniapp1") {
                    MiniApp1Activity.open(context = context)
                    protocol.sendMessage("")
                }
            }
        }
    }
}