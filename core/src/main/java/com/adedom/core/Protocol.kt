package com.adedom.core

interface MiniAppProtocol {
    var message: String?
    var listener: ((String?) -> Unit)?

    fun setOnClickListener(listener: (String?) -> Unit)
}

internal class MiniAppAdapter : MiniAppProtocol {
    override var message: String? = null
    override var listener: ((String?) -> Unit)? = null

    override fun setOnClickListener(listener: (String?) -> Unit) {
        this.listener = listener
    }
}
