package com.haru.member.exception.member

import com.haru.member.global.exception.code.ErrorCode
import org.springframework.http.HttpStatus

enum class MemberErrorCode(
    override val httpStatus: HttpStatus,
    override val code: String,
) : ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEM-001")
}