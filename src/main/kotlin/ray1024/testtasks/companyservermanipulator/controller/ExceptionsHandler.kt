package ray1024.testtasks.companyservermanipulator.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ray1024.testtasks.companyservermanipulator.exception.BusinessException
import ray1024.testtasks.companyservermanipulator.exception.ResourceNotFoundException
import ray1024.testtasks.companyservermanipulator.model.response.ErrorResponse

@ControllerAdvice
class ExceptionsHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFound(ex: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.allErrors.map { error ->
            when (error) {
                is FieldError -> "${error.field}: ${error.defaultMessage}"
                else -> error.defaultMessage
            }
        }
        val errorResponse = ErrorResponse(
            message = "Validation failed", details = errors
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            message = ex.message, details = ex.stackTrace.map { error -> error.toString() })
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}