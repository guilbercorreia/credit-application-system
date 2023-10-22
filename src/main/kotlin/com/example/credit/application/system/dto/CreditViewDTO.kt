package com.example.credit.application.system.dto

import com.example.credit.application.system.entity.Credit
import com.example.credit.application.system.enummeration.Status
import java.math.BigDecimal
import java.util.*

data class CreditViewDTO(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallments: Int,
    val status: Status,
    val emailCustomer: String?
) {
    constructor(credit: Credit): this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallments = credit.numberOfInstallments,
        status = credit.status,
        emailCustomer = credit.customer?.email
    )
}
