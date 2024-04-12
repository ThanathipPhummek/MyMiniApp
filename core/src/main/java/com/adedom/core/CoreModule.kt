package com.adedom.core

import android.widget.Toast
import org.koin.dsl.module

val coreModule = module {
    single<MiniAppProtocol> {
        MiniAppAdapter(
            listener = { message ->
                Toast.makeText(get(), message, Toast.LENGTH_SHORT).show()
            }
        )
    }
}
