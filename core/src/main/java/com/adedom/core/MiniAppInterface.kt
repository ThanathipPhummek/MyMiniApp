package com.adedom.core

import android.content.Context

interface MiniAppInterface {
    val listDefault: List<DefaultValue>
    var message: String?
    fun setDefaultCallback(defaultValue: DefaultValue?)
    fun sendMessage(message:String)
    fun initial(context: Context)
}