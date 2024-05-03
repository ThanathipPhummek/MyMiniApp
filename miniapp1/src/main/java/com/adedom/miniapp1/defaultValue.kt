package com.adedom.miniapp1

import com.adedom.core.MiniAppProtocol

fun defaultValue1(protocol: MiniAppProtocol) {
    protocol.setDefaultCallback(
        icon = "https://play-lh.googleusercontent.com/pXXiVNP9gg6Xd9mj9u-_2SglyG60mePjljwGKKYVcQlXH-WoXLKakDgV9wzyRfTTrcU=w240-h480-rw",
        appName = "มินิแอพ 1",
        appPath = "miniapp1",
        deeplink = "bugaboo://mini-app-1?send=MINIAPP1"
    )
}