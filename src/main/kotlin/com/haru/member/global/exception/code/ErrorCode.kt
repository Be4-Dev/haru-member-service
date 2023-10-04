package com.haru.member.global.exception.code

import org.springframework.http.HttpStatus

interface ErrorCode {
    val httpStatus: HttpStatus
    val code: String
}