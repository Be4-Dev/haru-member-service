package com.haru.member.global.exception

import com.haru.member.global.exception.code.CommonErrorCode
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private const val ERROR_LOG_FORMAT = "{} : {}"
    }

    private val log = LoggerFactory.getLogger(this::class.java)

    /* 비즈니스 로직 상에서 발생한 커스텀 예외 처리 */
    @ExceptionHandler(BusinessException::class)
    protected fun handleBindException(e: BusinessException): ResponseEntity<ErrorResponseDTO> {
        log.error(ERROR_LOG_FORMAT, e.javaClass.simpleName, e.message, e)

        val errorResponseDTO = ErrorResponseDTO(e.errorCode.code, e.message)

        return ResponseEntity
            .status(e.errorCode.httpStatus)
            .body(errorResponseDTO)
    }

    /* 기타 예외 처리 */
    @ExceptionHandler(Exception::class)
    protected fun handleException(e: Exception): ResponseEntity<ErrorResponseDTO> {
        val errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR
        val response = ErrorResponseDTO(
            errorCode.code,
            "서버 동작 중 알 수 없는 예외가 발생했습니다.",
        )

        return ResponseEntity
            .status(errorCode.httpStatus)
            .body(response)
    }
}