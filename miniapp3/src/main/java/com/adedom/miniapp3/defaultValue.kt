package com.adedom.miniapp3

import android.content.Context
import android.util.Log
import com.adedom.core.DefaultValue
import com.adedom.core.MiniAppInterface
import com.adedom.core.MiniAppProtocol
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

@OptIn(DelicateCoroutinesApi::class)
fun defaultValue3(context: Context){
    val protocol: MiniAppProtocol by inject(MiniAppProtocol::class.java)
//    val newDefaultValue = DefaultValue(icon = "https://fastly.picsum.photos/id/28/4928/3264.jpg?hmac=GnYF-RnBUg44PFfU5pcw_Qs0ReOyStdnZ8MtQWJqTfA", appName = "มินิแอพ 3", deeplink = "bugaboo://mini-app-3?send=MINIAPP3")
    val newDefaultValue = DefaultValue(icon = "https://fastly.picsum.photos/id/28/4928/3264.jpg?hmac=GnYF-RnBUg44PFfU5pcw_Qs0ReOyStdnZ8MtQWJqTfA", appName = "มินิแอพ 3", deeplink = "miniapp3")
    protocol.setDefaultCallback(newDefaultValue)

    GlobalScope.launch {
        var prevMessage = protocol.message
        while (true) {
            val newMessage = protocol.message
            if (newMessage != prevMessage) {
                Log.d("Counter", "MiniApp3: $newMessage")
                prevMessage = newMessage
                if(newMessage == "miniapp3"){
                    MiniApp3Activity.open(context = context)
                    protocol.sendMessage("")
                }
            }
        }
    }
}