package desafio.itau.backend.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotNull
import java.time.OffsetDateTime

data class TransactionRequestDTO(
    @field:NotNull
    @field:DecimalMin("0.0")
    val value: Double? = null,

    @field:NotNull
    val dateTime: OffsetDateTime? = null
)
