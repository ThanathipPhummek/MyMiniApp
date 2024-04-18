package com.adedom.miniapp1

import com.adedom.core.DefaultValue
import com.adedom.core.MiniAppProtocol
import org.koin.java.KoinJavaComponent.inject

fun defaultValue1(){
    val protocol: MiniAppProtocol by inject(MiniAppProtocol::class.java)
    val newDefaultValue = DefaultValue(icon = "https://picsum.photos/seed/picsum/200/200", appName = "มินิแอพ 1")
    protocol.setDefaultCallback(newDefaultValue)
}