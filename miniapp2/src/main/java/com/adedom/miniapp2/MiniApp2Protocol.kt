package com.adedom.miniapp2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MiniApp2Bundle(
    val message: String,
) : Parcelable

interface MiniApp2Protocol {
    var listener: (MiniApp2Bundle) -> Unit
}

class MiniApp2Adapter(
    override var listener: (MiniApp2Bundle) -> Unit,
) : MiniApp2Protocol
