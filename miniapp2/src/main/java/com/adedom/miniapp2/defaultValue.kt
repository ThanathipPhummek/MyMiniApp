package com.adedom.miniapp2

import com.adedom.core.MiniAppProtocol

fun defaultValue2(protocol: MiniAppProtocol) {
    protocol.setDefaultCallback(
        icon = "https://play-lh.googleusercontent.com/OEj7dn7JC75ti5pENMOUZ1pq9PvDONPx4FtYlDPTaELw8S1-pC-XXjB7lbwZZv6vXFw=w480-h960-rw",
        appName = "มินิแอพ 2",
        appPath = "miniapp2",
        deeplink = "bugaboo://mini-app-2?send=MINIAPP2"
    )
}