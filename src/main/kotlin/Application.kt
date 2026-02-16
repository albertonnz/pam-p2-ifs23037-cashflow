package org.delcom

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.delcom.routes.CashFlowRoutes
import org.delcom.config.configureSerialization

fun main() {
    embeddedServer(Netty, port = 8080) {

        // wajib supaya JSON bisa dikirim/diterima
        configureSerialization()

        // routing endpoint cashflow
        CashFlowRoutes()

    }.start(wait = true)
}
