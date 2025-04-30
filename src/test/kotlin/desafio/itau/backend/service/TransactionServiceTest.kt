package desafio.itau.backend.service

import desafio.itau.backend.config.StatisticsProperties
import desafio.itau.backend.dto.TransactionRequestDTO
import desafio.itau.backend.exception.InvalidTransactionException
import desafio.itau.backend.repository.TransactionRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.OffsetDateTime
import desafio.itau.backend.service.TransactionService

class TransactionServiceTest {

    private lateinit var service: TransactionService
    private lateinit var repository: TransactionRepository

    @BeforeEach
    fun setup() {
        repository = TransactionRepository()

        val properties = StatisticsProperties().apply {
            windowOnSeconds = 60
        }

        service = TransactionService(repository, properties)
    }

    @Test
    fun `Should save valid transactions`() {
        val dto = TransactionRequestDTO(
            value = 100.0,
            dateTime = OffsetDateTime.now().minusSeconds(10)
        )

        service.save(dto)

        val stats = service.getStatistics()
        assertEquals(1, stats.count)
        assertEquals(100.0, stats.sum)
    }

    @Test
    fun `Should throw exception for negative values`() {
        val dto = TransactionRequestDTO(
            value = -10.0,
            dateTime = OffsetDateTime.now().minusSeconds(5)
        )

        assertThrows(InvalidTransactionException::class.java) {
            service.save(dto)
        }
    }

    @Test
    fun `Should throw an exception for future dates`() {
        val dto = TransactionRequestDTO(
            value = 10.0,
            dateTime = OffsetDateTime.now().plusSeconds(30)
        )

        assertThrows(InvalidTransactionException::class.java) {
            service.save(dto)
        }
    }

    @Test
    fun `Should clear all the transactions`() {
        val dto = TransactionRequestDTO(
            value = 50.0,
            dateTime = OffsetDateTime.now().minusSeconds(10)
        )
        service.save(dto)
        service.clear()

        val stats = service.getStatistics()
        assertEquals(0, stats.count)
        assertEquals(0.0, stats.sum)
    }
}
