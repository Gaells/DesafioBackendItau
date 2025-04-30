package desafio.itau.backend.model

import java.time.OffsetDateTime

data class Transaction(
    val value: Double,
    val dateTime: OffsetDateTime
)
