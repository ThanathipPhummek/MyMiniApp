package com.adedom.core

import android.util.Log

interface MiniAppProtocol {
    var message: String?
    var listener: ((String?) -> Unit)?
    var defaultSetter: ((DefaultValue?) -> Unit)?
    val listDefault: List<DefaultValue>

    fun setOnClickListener(listener: (String?) -> Unit)
    fun setDefaultCallback(defaultValue: DefaultValue?)
}

internal class MiniAppAdapter : MiniAppProtocol {
    override var message: String? = null
    override var listener: ((String?) -> Unit)? = null
    override var defaultSetter: ((DefaultValue?) -> Unit)? = null
    override var listDefault: MutableList<DefaultValue> = mutableListOf()

    override fun setOnClickListener(listener: (String?) -> Unit) {
        this.listener = listener
    }

    override fun setDefaultCallback(defaultValue: DefaultValue?) {
        defaultValue?.let {
            this.listDefault.add(it)
        }
    }
}

data class DefaultValue(
    val icon: String?,
    val appName: String?,
    val deeplink: String?
)