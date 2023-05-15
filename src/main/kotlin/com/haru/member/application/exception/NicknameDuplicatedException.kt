package com.haru.member.application.exception

import org.springframework.graphql.execution.ErrorType
import org.springframework.http.HttpStatus

class NicknameDuplicatedException(nickname: String) : BusinessException(
    errorCode   = MemberErrorCode.NICKNAME_DUPLICATED.code,
    httpStatus  = HttpStatus.CONFLICT,
    errorType   = ErrorType.BAD_REQUEST,
    message     = MemberErrorCode.NICKNAME_DUPLICATED.message + "(nickname: $nickname)",
)