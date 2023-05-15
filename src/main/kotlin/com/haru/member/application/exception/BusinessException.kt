package com.haru.member.application.exception

import graphql.ErrorClassification
import org.springframework.http.HttpStatus

abstract class BusinessException protected constructor(
    val httpStatus  : HttpStatus,
    val errorCode   : String,
    val errorType   : ErrorClassification,
    message         : String,
) : RuntimeException(message)