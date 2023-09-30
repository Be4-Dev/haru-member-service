package com.haru.member.global.exception

import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler(
    private val messageSource: MessageSource,
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    // binding error가 발생할 경우
    @ExceptionHandler(BusinessException::class)
    protected fun handleBindException(e: BusinessException): ResponseEntity<ErrorResponseDTO> {
        log.error(ERROR_LOG_MESSAGE, e.javaClass.simpleName, e.message, e)

        val errorResponseDTO = ErrorResponseDTO(e.errorCode.code, e.message)

        return ResponseEntity.status(e.errorCode.httpStatus)
            .body(errorResponseDTO)
    }

    // 나머지 예외 발생
    @ExceptionHandler(Exception::class)
    protected fun handleException(e: Exception): ResponseEntity<ErrorResponseDTO> {

        val message = messageSource.getMessage("internal.server.error", null, LocaleContextHolder.getLocale())
        log.error(ERROR_LOG_MESSAGE, e.javaClass.simpleName, message, e)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponseDTO(
                    "ERR-999",
                    message,
                )
            )
    }

    companion object {
        private const val ERROR_LOG_MESSAGE = "[ERROR] {} : {}"
    }
}