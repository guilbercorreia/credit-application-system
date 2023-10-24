package com.example.credit.application.system.controller

import com.example.credit.application.system.dto.CustomerDTO
import com.example.credit.application.system.dto.CustomerUpdateDTO
import com.example.credit.application.system.dto.CustomerViewDTO
import com.example.credit.application.system.service.impl.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDTO): ResponseEntity<String> {
        val savedCustomer = customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerViewDTO> {
        val customer = customerService.findById(id)
        return ResponseEntity.ok(CustomerViewDTO(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long):ResponseEntity<Any>{
        customerService.delete(id)
        return ResponseEntity.ok().build()
    }

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody customerUpdateDto: CustomerUpdateDTO): ResponseEntity<CustomerViewDTO> {
        val customer = customerService.findById(id)
        val customerToUpdate = customerUpdateDto.toEntity(customer)
        val customerUpdated = customerService.save(customerToUpdate)
        return ResponseEntity.ok(CustomerViewDTO(customerUpdated))
    }
}