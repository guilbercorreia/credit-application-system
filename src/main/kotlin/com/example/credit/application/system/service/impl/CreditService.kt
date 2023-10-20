package com.example.credit.application.system.service.impl


import com.example.credit.application.system.entity.Credit
import com.example.credit.application.system.repository.CreditRepository
import com.example.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> = creditRepository.findAllByCustomerId(customerId)
    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
       val credit: Credit = creditRepository.findByCreditCode(creditCode) ?: throw RuntimeException("CreditCode $creditCode not found")
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("id not matching with credit code")
    }
}