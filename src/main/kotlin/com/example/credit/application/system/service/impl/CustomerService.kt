package com.example.credit.application.system.service.impl

import com.example.credit.application.system.entity.Customer
import com.example.credit.application.system.repository.CustomerRepository
import com.example.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer = customerRepository.save(customer)

    override fun findById(id: Long): Customer = customerRepository.findById(id).orElseThrow{
        throw RuntimeException("ID $id not found")
    }

    override fun delete(id: Long) = customerRepository.deleteById(id)
}