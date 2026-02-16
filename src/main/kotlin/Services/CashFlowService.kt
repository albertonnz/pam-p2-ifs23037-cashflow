package org.delcom.services

import org.delcom.entities.CashFlow
import java.util.*

class CashFlowService {

    private val cashFlows = mutableListOf<CashFlow>()

    fun getAllCashFlows(): List<CashFlow> = cashFlows

    fun getCashFlowById(id: String): CashFlow? =
        cashFlows.find { it.id == id }

    fun createCashFlow(
        type: String,
        source: String,
        label: String,
        amount: Int,
        description: String
    ): String {
        val id = UUID.randomUUID().toString()

        val cashFlow = CashFlow(
            id,
            type,
            source,
            label,
            amount,
            description,
            Date().toString(),
            Date().toString()
        )

        cashFlows.add(cashFlow)
        return id
    }

    fun createRawCashFlow(
        id: String,
        type: String,
        source: String,
        label: String,
        amount: Int,
        description: String,
        createdAt: String,
        updatedAt: String
    ) {
        cashFlows.add(
            CashFlow(
                id, type, source, label,
                amount, description,
                createdAt, updatedAt
            )
        )
    }

    fun removeCashFlow(id: String) {
        cashFlows.removeIf { it.id == id }
    }
}
