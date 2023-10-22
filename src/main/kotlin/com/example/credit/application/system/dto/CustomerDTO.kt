package com.example.credit.application.system.dto

import com.example.credit.application.system.entity.Address
import com.example.credit.application.system.entity.Customer
import java.math.BigDecimal

data class CustomerDTO(
    val firstName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val password: String,
    val zipcode: String,
    val street: String
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipcode,
            street = this.street
        )
    )
}