package com.haru.member.global.exception

import org.springframework.validation.BindingResult

data class ErrorResponseDTO(
    val code: String,
    val message: String? = null,
)