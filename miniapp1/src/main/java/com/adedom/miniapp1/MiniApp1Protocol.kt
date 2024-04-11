package com.adedom.miniapp1

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MiniApp1Bundle(
    val message: String,
) : Parcelable

interface MiniApp1Protocol {
    fun open(context: Context, bundle: MiniApp1Bundle)
    var listener: (MiniApp1Bundle) -> Unit
}

class MiniApp1Adapter(
    override var listener: (MiniApp1Bundle) -> Unit,
) : MiniApp1Protocol {
    override fun open(context: Context, bundle: MiniApp1Bundle) {
        MiniApp1Activity.open(
            context = context,
            bundle = bundle,
            protocol = this,
        )
    }
}
