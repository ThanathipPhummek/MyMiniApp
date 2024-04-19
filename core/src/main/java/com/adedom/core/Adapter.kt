package com.adedom.core

internal class AppAdapter : MiniAppProtocol,MainAppProtocol {
    override var logCount: Int? = null
    override var message: String? = null
    override var listener: ((String?) -> Unit)? = null
    override var defaultSetter: ((DefaultValue?) -> Unit)? = null
    override var listDefault: MutableList<DefaultValue> = mutableListOf()
    override val saveLog: MutableList<String> = mutableListOf()

    override fun setOnClickListener(listener: (String?) -> Unit) {
        this.listener = listener
    }

    override fun setDefaultCallback(defaultValue: DefaultValue?) {
        defaultValue?.let {
            if(defaultValue.icon?.isNotEmpty() == true &&
                defaultValue.appName?.isNotEmpty() == true &&
                defaultValue.deeplink?.isNotEmpty() == true)
                this.listDefault.add(it)
        }
    }

    override fun saveLogListener(log: String) {
        this.saveLog.add(log)
    }

    override fun logCountListener(log: List<String>,path:String) {
        val countMiniApp1 = log.count { it == "MiniApp1" }
        val countMiniApp2 = log.count { it == "MiniApp2" }
        val countMiniApp3 = log.count { it == "MiniApp3" }
        when (path) {
            "miniapp1" -> {
                this.logCount = countMiniApp1
            }
            "miniapp2" -> {
                this.logCount = countMiniApp2
            }
            else -> {
                this.logCount = countMiniApp3
            }
        }
    }
}

data class DefaultValue(
    val icon: String?,
    val appName: String?,
    val deeplink: String?
)