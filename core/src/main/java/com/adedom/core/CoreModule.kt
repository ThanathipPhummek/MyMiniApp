package com.adedom.core

import org.koin.dsl.module

val coreModule = module {
    single<MiniAppProtocol> { MiniAppAdapter() }
}
