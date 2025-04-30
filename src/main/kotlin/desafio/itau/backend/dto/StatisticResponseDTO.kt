package desafio.itau.backend.dto

data class StatisticResponseDTO(
  val count: Long,
  val sum: Double,
  val avg: Double,
  val min: Double,
  val max: Double
)