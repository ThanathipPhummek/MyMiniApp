package com.adedom.miniapp3

import com.adedom.core.DefaultValue
import com.adedom.core.MiniAppProtocol
import org.koin.java.KoinJavaComponent.inject

fun defaultValue3(){
    val protocol: MiniAppProtocol by inject(MiniAppProtocol::class.java)
    val newDefaultValue = DefaultValue(icon = "https://fastly.picsum.photos/id/28/4928/3264.jpg?hmac=GnYF-RnBUg44PFfU5pcw_Qs0ReOyStdnZ8MtQWJqTfA", appName = "มินิแอพ 3", deeplink = "bugaboo://mini-app-3?send=MINIAPP3")
    protocol.setDefaultCallback(newDefaultValue)
}