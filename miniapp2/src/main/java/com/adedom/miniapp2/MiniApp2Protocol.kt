package com.adedom.miniapp2

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MiniApp2Bundle(
    val message: String,
) : Parcelable

interface MiniApp2Protocol {
    fun open(context: Context, bundle: MiniApp2Bundle)
    var listener: (MiniApp2Bundle) -> Unit
}

class MiniApp2Adapter(
    override var listener: (MiniApp2Bundle) -> Unit,
) : MiniApp2Protocol {
    override fun open(context: Context, bundle: MiniApp2Bundle) {
        MiniApp2Activity.open(
            context = context,
            bundle = bundle,
            protocol = this,
        )
    }
}
