package com.haru.member.global.exception

import com.haru.member.global.exception.code.ErrorCode

open class BusinessException(
    val errorCode: ErrorCode,
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)