package com.adedom.core

interface MainAppProtocol {
    var message: String?
    var text: String?
    var listener: ((String?) -> Unit)?
    var defaultSetter: ((DefaultValue?) -> Unit)?
    val listDefault: List<DefaultValue>
    val saveLog: List<String>
    var logCount: Int?

    fun setOnClickListener(listener: (String?) -> Unit)
    fun setDefaultCallback(icon: String?, appName: String?,appPath:String?, deeplink: String?)
    fun saveLogListener(log: String)
    fun sendMessage(message:String)
    fun sendText(text:String)
}