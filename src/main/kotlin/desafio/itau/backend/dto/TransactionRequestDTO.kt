package desafio.itau.backend.dto

import java.time.OffsetDateTime

data class TransactionRequestDTO(
    val value: Double?,
    val dateTime: OffsetDateTime?
)
