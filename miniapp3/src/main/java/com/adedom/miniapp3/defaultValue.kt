package com.adedom.miniapp3

import com.adedom.core.MiniAppProtocol

fun defaultValue3(protocol: MiniAppProtocol) {
    protocol.setDefaultCallback(
        icon = "https://play-lh.googleusercontent.com/ddvGZfLXr0Y4vJU88OY8elKSqr-XQ12vczKatmAXt03MuO8FRDA8W6fFw361Z8TCJw=w240-h480-rw",
        appName = "มินิแอพ 3",
        appPath = "miniapp3",
        deeplink = "bugaboo://mini-app-3?send=MINIAPP3"
    )
}