package org.delcom.controllers

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.delcom.data.DataResponse
import org.delcom.helpers.loadInitialData
import org.delcom.services.CashFlowService

class CashFlowsController(
    private val service: CashFlowService
) {

    suspend fun setupData(call: ApplicationCall) {

        val oldData = service.getAllCashFlows()
        oldData.forEach {
            service.removeCashFlow(it.id)
        }

        val initCashFlows = loadInitialData()

        initCashFlows.forEach {
            service.createRawCashFlow(
                it.id,
                it.type,
                it.source,
                it.label,
                it.amount,
                it.description,
                it.createdAt,
                it.updatedAt
            )
        }

        call.respond(
            DataResponse("success", "Berhasil memuat data awal", null)
        )
    }

    suspend fun getAll(call: ApplicationCall) {
        call.respond(
            DataResponse(
                "success",
                "Data cashflows",
                service.getAllCashFlows()
            )
        )
    }

    suspend fun create(call: ApplicationCall) {

        val body = call.receive<Map<String, String>>()

        val id = service.createCashFlow(
            body["type"] ?: "",
            body["source"] ?: "",
            body["label"] ?: "",
            body["amount"]?.toInt() ?: 0,
            body["description"] ?: ""
        )

        call.respond(
            DataResponse(
                "success",
                "Berhasil tambah data",
                mapOf("cashFlowId" to id)
            )
        )
    }

    suspend fun delete(call: ApplicationCall) {
        val id = call.parameters["id"] ?: ""

        service.removeCashFlow(id)

        call.respond(
            DataResponse("success", "Berhasil hapus data", null)
        )
    }
}
