package com.adedom.core

internal class AppAdapter : MiniAppProtocol, MainAppProtocol {
    override var logCount: Int? = null
    override var message: String? = null
    override var text: String? = null
    override var listener: ((String?) -> Unit)? = null
    override var defaultSetter: ((DefaultValue?) -> Unit)? = null
    override var listDefault: MutableList<DefaultValue> = mutableListOf()
    override val saveLog: MutableList<String> = mutableListOf()

    override fun setOnClickListener(listener: (String?) -> Unit) {
        this.listener = listener
    }

    override fun setDefaultCallback(icon: String?, appName: String?,appPath:String?, deeplink: String?) {
        val defaultValue = DefaultValue(icon, appName, appPath, deeplink)
        defaultValue?.let {
            if(it.icon?.isNotEmpty() == true &&
                it.appName?.isNotEmpty() == true &&
                it.deeplink?.isNotEmpty() == true)
                this.listDefault.add(it)
        }
    }

    override fun saveLogListener(log: String) {
        this.saveLog.add(log)
    }

    override fun sendMessage(message: String) {
        this.message = message
    }

    override fun sendText(text: String) {
        this.text = text
    }
}

data class DefaultValue(
    val icon: String?,
    val appName: String?,
    val appPath: String?,
    val deeplink: String?
)




