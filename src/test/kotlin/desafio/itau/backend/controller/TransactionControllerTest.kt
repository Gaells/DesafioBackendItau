package desafio.itau.backend.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import desafio.itau.backend.dto.StatisticResponseDTO
import desafio.itau.backend.dto.TransactionRequestDTO
import desafio.itau.backend.exception.InvalidTransactionException
import desafio.itau.backend.service.TransactionService
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.time.OffsetDateTime

@WebMvcTest(TransactionController::class)
class TransactionControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var transactionService: TransactionService

    private val objectMapper = jacksonObjectMapper().findAndRegisterModules()

    @Test
    fun `deve retornar 201 ao salvar transacao valida`() {
        val dto = TransactionRequestDTO(100.0, OffsetDateTime.now().minusSeconds(5))

        mockMvc
            .post("/transacao") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(dto)
            }
            .andExpect {
                status { isCreated() }
            }

        verify(transactionService).save(any())
    }

    @Test
    fun `deve retornar 422 ao salvar transacao invalida`() {
        whenever(transactionService.save(any())).thenThrow(InvalidTransactionException())

        val dto = TransactionRequestDTO(100.0, OffsetDateTime.now().plusDays(1))

        mockMvc
            .post("/transacao") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(dto)
            }
            .andExpect {
                status { isUnprocessableEntity() }
            }
    }

    @Test
    fun `deve retornar 400 com JSON malformado`() {
        val jsonInvalido = """ { "value": "abc", "dateTime": 123 } """

        mockMvc
            .post("/transacao") {
                contentType = MediaType.APPLICATION_JSON
                content = jsonInvalido
            }
            .andExpect {
                status { isBadRequest() }
            }
    }

    @Test
    fun `deve retornar 200 ao limpar transacoes`() {
        mockMvc
            .delete("/transacao")
            .andExpect {
                status { isOk() }
            }

        verify(transactionService).clear()
    }

    @Test
    fun `deve retornar 200 ao buscar estatisticas`() {
        whenever(transactionService.getStatistics()).thenReturn(
            StatisticResponseDTO(
                count = 1,
                sum = 100.0,
                avg = 100.0,
                min = 100.0,
                max = 100.0
            )
        )

        mockMvc
            .get("/estatistica")
            .andExpect {
                status { isOk() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(
                        """
                            {
                                "count": 1,
                                "sum": 100.0,
                                "avg": 100.0,
                                "min": 100.0,
                                "max": 100.0
                            }
                        """.trimIndent(),
                        strict = false
                    )
                }
            }

        verify(transactionService).getStatistics()
    }
}
