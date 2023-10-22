package com.example.credit.application.system.controller

import com.example.credit.application.system.dto.CreditDTO
import com.example.credit.application.system.dto.CreditViewDTO
import com.example.credit.application.system.dto.CreditViewListDTO
import com.example.credit.application.system.entity.Credit
import com.example.credit.application.system.service.impl.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResource(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody creditDto: CreditDTO): ResponseEntity<String> {
        val credit = creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Credit ${credit.creditCode} - Customer ${credit.customer?.firstName} saved!")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewListDTO>> {
        val list: List<CreditViewListDTO> =
            creditService.findAllByCustomer(customerId).stream().map { credit: Credit -> CreditViewListDTO(credit) }
                .collect(Collectors.toList())
        return ResponseEntity.ok(list)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(
        @RequestParam(value = "customerId") customerId: Long, @PathVariable creditCode: UUID): ResponseEntity<CreditViewDTO> {
        val credit = creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.ok(CreditViewDTO(credit))
    }
}