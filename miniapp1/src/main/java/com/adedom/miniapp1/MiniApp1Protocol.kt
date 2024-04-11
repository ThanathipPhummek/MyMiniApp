package com.adedom.miniapp1

import android.app.Activity
import android.content.Context
import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.parcelize.Parcelize

@Parcelize
data class MiniApp1Bundle(
    val message: String,
) : Parcelable

interface MiniApp1Protocol {
    var listener: (MiniApp1Bundle) -> Unit
    fun open(context: Context, bundle: MiniApp1Bundle)
    fun close(activity: Activity)

    @Composable
    fun MiniAppIcon(modifier: Modifier = Modifier) {
    }
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

    override fun close(activity: Activity) {
        activity.finish()
    }

    @Composable
    override fun MiniAppIcon(modifier: Modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_android),
                contentDescription = null,
            )
            Text(text = "MiniApp1")
        }
    }
}
