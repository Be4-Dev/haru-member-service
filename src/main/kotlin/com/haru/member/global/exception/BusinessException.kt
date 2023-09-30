package com.haru.member.global.exception

open class BusinessException(
    val errorCode: ErrorCode,
    override val message: String,
    override val cause: Throwable? = null,
) : RuntimeException(message, cause)