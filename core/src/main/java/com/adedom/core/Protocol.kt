package com.adedom.core

interface MiniAppProtocol {
    var message: String?
    var listener: (String?) -> Unit
}

internal class MiniAppAdapter(
    override var listener: (String?) -> Unit,
) : MiniAppProtocol {
    override var message: String? = null
}
