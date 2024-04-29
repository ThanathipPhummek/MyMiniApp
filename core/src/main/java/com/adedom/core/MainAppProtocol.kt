package com.adedom.core

interface MainAppProtocol {
    var message: String?
    var listener: ((String?) -> Unit)?
    var defaultSetter: ((DefaultValue?) -> Unit)?
    val listDefault: List<DefaultValue>
    val saveLog: List<String>
    var logCount: Int?

    fun setOnClickListener(listener: (String?) -> Unit)
    fun setDefaultCallback(defaultValue: DefaultValue?)
    fun saveLogListener(log: String)
    fun sendMessage(message:String)
}