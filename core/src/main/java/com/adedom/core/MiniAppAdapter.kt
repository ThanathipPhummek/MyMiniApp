package com.adedom.core

internal class MiniAppAdapter(
    private val mainAppAdapter: MainAppProtocol
) : MiniAppProtocol {
    override var logCount: Int? = null
    override var message: String? = null
    override var listener: ((String?) -> Unit)? = null
    override var defaultSetter: ((DefaultValue?) -> Unit)? = null
    override var listDefault: MutableList<DefaultValue> = mutableListOf()
    override val saveLog: MutableList<String> = mutableListOf()

    override fun setOnClickListener(listener: (String?) -> Unit) {
        this.listener = listener
    }

    override fun setDefaultCallback(icon: String?, appName: String?,appPath:String?, deeplink: String?) {
        mainAppAdapter.setDefaultCallback(icon, appName, appPath, deeplink)
    }

    override fun saveLogListener(log: String) {
        this.saveLog.add(log)
    }

    override fun sendMessage(message: String) {
        this.message = mainAppAdapter.message
    }

    override fun getText(): String{
        return mainAppAdapter.text?:""
    }
}

data class DefaultValue(
    val icon: String?,
    val appName: String?,
    val appPath: String?,
    val deeplink: String?
)




