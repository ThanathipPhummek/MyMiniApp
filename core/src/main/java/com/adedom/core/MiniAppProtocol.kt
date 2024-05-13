package com.adedom.core

interface MiniAppProtocol {
    var message: String?
    var listener: ((String?) -> Unit)?
    var defaultSetter: ((DefaultValue?) -> Unit)?
    val listDefault: List<DefaultValue>
    val saveLog: List<String>
    var logCount: Int?

    fun setOnClickListener(listener: (String?) -> Unit)
    fun setDefaultCallback(icon: String?, appName: String?,appPath:String?, deeplink: String?)
    fun saveLogListener(log: String)
    fun sendMessage(message:String)
    fun getText():String 
}