package com.haru.member.application.exception

enum class MemberErrorCode(val code: String, val message: String) {
    NICKNAME_DUPLICATED (code = "MEM_001", message = "닉네임이 이미 존재합니다."),
    EMAIL_DUPLICATED    (code = "MEM_002", message = "이메일이 이미 존재합니다."),
}