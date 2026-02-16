package org.delcom.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.delcom.controllers.CashFlowsController
import org.delcom.services.CashFlowService

fun Application.configureRouting() {

    val service = CashFlowService()
    val controller = CashFlowsController(service)

    routing {
        route("/cash-flows") {

            post("/setup") {
                controller.setupData(call)
            }

            get {
                controller.getAll(call)
            }

            post {
                controller.create(call)
            }
        }
    }
}
