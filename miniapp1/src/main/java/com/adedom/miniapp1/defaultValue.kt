package com.adedom.miniapp1

import com.adedom.core.DefaultValue
import com.adedom.core.MiniAppProtocol
import org.koin.java.KoinJavaComponent.inject

fun defaultValue1(){
    val protocol: MiniAppProtocol by inject(MiniAppProtocol::class.java)
    val newDefaultValue = DefaultValue(icon = "https://fastly.picsum.photos/id/16/2500/1667.jpg?hmac=uAkZwYc5phCRNFTrV_prJ_0rP0EdwJaZ4ctje2bY7aE", appName = "มินิแอพ 1", deeplink = "bugaboo://mini-app-1?send=MINIAPP1")
    protocol.setDefaultCallback(newDefaultValue)
}