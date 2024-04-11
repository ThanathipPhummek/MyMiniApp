package com.adedom.miniapp2

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
data class MiniApp2Bundle(
    val message: String,
) : Parcelable

interface MiniApp2Protocol {
    var listener: (MiniApp2Bundle) -> Unit
    fun open(context: Context, bundle: MiniApp2Bundle)
    fun close(activity: Activity)

    @Composable
    fun MiniAppIcon(modifier: Modifier = Modifier) {
    }
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
                painter = painterResource(id = R.drawable.ic_smartphone),
                contentDescription = null,
            )
            Text(text = "MiniApp2")
        }
    }
}
