package com.haru.member.domain

import java.time.LocalDateTime

abstract class BaseEntity(
    var createdAt: LocalDateTime?   = null,
    var createdBy: String,
    var updatedAt: LocalDateTime?   = null,
    var updatedBy: String?          = null,
)