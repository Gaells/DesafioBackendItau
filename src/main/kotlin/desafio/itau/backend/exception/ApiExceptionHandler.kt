package desafio.itau.backend.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(InvalidTransactionException::class)
    fun handleInvalidTransaction(): ResponseEntity<Void> {
        return ResponseEntity.unprocessableEntity().build()
    }

    @ExceptionHandler(
        MethodArgumentNotValidException::class,
        MethodArgumentTypeMismatchException::class,
        IllegalArgumentException::class
    )
    fun handleValidationError(): ResponseEntity<Void> {
        return ResponseEntity.badRequest().build()
    }
}
