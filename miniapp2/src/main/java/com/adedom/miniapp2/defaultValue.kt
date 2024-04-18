package com.adedom.miniapp2

import android.util.Log
import com.adedom.core.DefaultValue
import com.adedom.core.MiniAppProtocol
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

fun defaultValue2(){
    val protocol: MiniAppProtocol by inject(MiniAppProtocol::class.java)
//    protocol.defaultSetter?.invoke(DefaultValue("https://picsum.photos/seed/picsum/200/200","มินิแอพ 2"))
    val newDefaultValue = DefaultValue(icon = "https://picsum.photos/seed/picsum/200/200", appName = "มินิแอพ 2")
    protocol.setDefaultCallback(newDefaultValue)
}