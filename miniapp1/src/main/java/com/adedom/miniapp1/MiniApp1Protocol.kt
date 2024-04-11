package com.adedom.miniapp1

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MiniApp1Bundle(
    val message: String,
) : Parcelable

interface MiniApp1Protocol {
    var listener: (MiniApp1Bundle) -> Unit
}

class MiniApp1Adapter(
    override var listener: (MiniApp1Bundle) -> Unit,
) : MiniApp1Protocol
