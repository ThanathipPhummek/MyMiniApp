package com.adedom.miniapp2

import android.content.Context
import android.util.Log
import com.adedom.core.DefaultValue
import com.adedom.core.MiniAppProtocol
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

@OptIn(DelicateCoroutinesApi::class)
fun defaultValue2(context:Context){
    val protocol: MiniAppProtocol by inject(MiniAppProtocol::class.java)
//    val newDefaultValue = DefaultValue(icon = "https://fastly.picsum.photos/id/22/4434/3729.jpg?hmac=fjZdkSMZJNFgsoDh8Qo5zdA_nSGUAWvKLyyqmEt2xs0", appName = "มินิแอพ 2", deeplink = "bugaboo://mini-app-2?send=MINIAPP2")
    val newDefaultValue = DefaultValue(icon = "https://fastly.picsum.photos/id/22/4434/3729.jpg?hmac=fjZdkSMZJNFgsoDh8Qo5zdA_nSGUAWvKLyyqmEt2xs0", appName = "มินิแอพ 2", deeplink = "miniapp2")
    protocol.setDefaultCallback(newDefaultValue)

    GlobalScope.launch {
        var prevMessage = protocol.message
        while (true) {
            val newMessage = protocol.message
            if (newMessage != prevMessage) {
                Log.d("Counter", "MiniApp2: $newMessage")
                prevMessage = newMessage
                if(newMessage == "miniapp2"){
                    MiniApp2Activity.open(context = context)
                    protocol.sendMessage("")
                }
            }
        }
    }
}