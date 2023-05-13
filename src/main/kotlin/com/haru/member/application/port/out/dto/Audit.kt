package com.haru.member.application.port.out.dto

import java.time.LocalDateTime

data class Audit( //@formatter:off
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?   = null,
    val createdBy: String,
    val updatedBy: String?          = null,
)