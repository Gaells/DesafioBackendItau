package desafio.itau.backend.controller

import desafio.itau.backend.dto.TransactionRequestDTO
import desafio.itau.backend.dto.StatisticResponseDTO
import desafio.itau.backend.Service.TransactionService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class TransactionController(
    private val transactionService: TransactionService
) {

    @PostMapping("/transaction")
    fun create(@Valid @RequestBody request: TransactionRequestDTO): ResponseEntity<Void> {
        transactionService.save(request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping("/transaction")
    fun deleteAll(): ResponseEntity<Void> {
        transactionService.clear()
        return ResponseEntity.ok().build()
    }

    @GetMapping("/statistics")
    fun getStatistics(): ResponseEntity<StatisticResponseDTO> {
        val stats = transactionService.getStatistics()
        return ResponseEntity.ok(stats)
    }
}
