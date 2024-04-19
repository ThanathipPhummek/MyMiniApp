package com.adedom.miniapp2

import com.adedom.core.DefaultValue
import com.adedom.core.MiniAppProtocol
import org.koin.java.KoinJavaComponent.inject

fun defaultValue2(){
    val protocol: MiniAppProtocol by inject(MiniAppProtocol::class.java)
    val newDefaultValue = DefaultValue(icon = "https://fastly.picsum.photos/id/22/4434/3729.jpg?hmac=fjZdkSMZJNFgsoDh8Qo5zdA_nSGUAWvKLyyqmEt2xs0", appName = "มินิแอพ 2", deeplink = "bugaboo://mini-app-2?send=MINIAPP2")
    protocol.setDefaultCallback(newDefaultValue)
}