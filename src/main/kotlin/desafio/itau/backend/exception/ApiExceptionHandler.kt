package desafio.itau.backend.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(InvalidTransactionException::class)
    fun handleInvalidTransaction(): ResponseEntity<Void> {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build()
    }

    @ExceptionHandler(Exception::class)
    fun handleBadRequest(): ResponseEntity<Void> {
        return ResponseEntity.badRequest().build()
    }
}
