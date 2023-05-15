package com.haru.member.application.exception

import org.springframework.graphql.execution.ErrorType
import org.springframework.http.HttpStatus

class EmailDuplicatedException(email: String) : BusinessException(
    errorCode   = MemberErrorCode.EMAIL_DUPLICATED.code,
    httpStatus  = HttpStatus.CONFLICT,
    errorType   = ErrorType.BAD_REQUEST,
    message     = MemberErrorCode.EMAIL_DUPLICATED.message + " (email: $email)",
)