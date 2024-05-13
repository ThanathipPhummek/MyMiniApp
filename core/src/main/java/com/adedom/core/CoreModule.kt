package com.adedom.core

import org.koin.core.qualifier.named
import org.koin.dsl.module

val coreModule = module {
//    single<MainAppProtocol>(named("mainprotocol")) { MainAppAdapter() }
    single<MainAppProtocol> { MainAppAdapter() }
    single<MiniAppProtocol> { MiniAppAdapter(get()) }
}