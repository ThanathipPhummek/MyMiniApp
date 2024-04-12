package com.adedom.core

interface MiniAppProtocol {
    var message: String?
}

internal class MiniAppAdapter : MiniAppProtocol {
    override var message: String? = null
}
