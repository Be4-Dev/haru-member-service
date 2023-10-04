package com.haru.member.global.exception

data class ErrorResponseDTO(
    val code: String,
    val message: String? = null,
)