package desafio.itau.backend.Service

import desafio.itau.backend.dto.StatisticResponseDTO
import desafio.itau.backend.dto.TransactionRequestDTO
import desafio.itau.backend.exception.InvalidTransactionException
import desafio.itau.backend.model.Transaction
import desafio.itau.backend.repository.TransactionRepository
import org.springframework.stereotype.Service
import java.time.OffsetDateTime

@Service
class TransactionService (
  private val repository: TransactionRepository
) {

  fun save(dto: TransactionRequestDTO) {
    val value = dto.value ?: throw InvalidTransactionException()
    val dateTime = dto.dateTime ?: throw InvalidTransactionException()

    if(value < 0 || dateTime.isAfter(OffsetDateTime.now())) {
      throw InvalidTransactionException()
    }

    repository.add(Transaction(value, dateTime))
  }

  fun clear() = repository.clear()

  fun getStatistics(): StatisticResponseDTO  {
    val now = OffsetDateTime.now()
    val lastMinute = repository.list().filter { it.dateTime.isAfter(now.minusSeconds(60)) }

    val count = lastMinute.size.toLong()
    val sum = lastMinute.sumOf { it.value }
    val avg = if(count > 0) sum / count else 0.0
    val min = lastMinute.minOfOrNull { it.value } ?: 0.0
    val max = lastMinute.maxOfOrNull { it.value } ?: 0.0

    return StatisticResponseDTO(count, sum, avg, min, max)
  }
}
