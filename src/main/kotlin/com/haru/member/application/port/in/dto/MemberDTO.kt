package com.haru.member.application.port.`in`.dto

import com.haru.member.domain.Member
import java.time.LocalDateTime

//@formatter:off

data class MemberDTO(
    val id          : Long?,
    val nickname    : String,
    val email       : String,
    val createdAt   : LocalDateTime?,
    val createdBy   : String,
    val updatedAt   : LocalDateTime?,
    val updatedBy   : String?,
) {
    companion object {
        fun from(member: Member): MemberDTO {
            return MemberDTO(
                id          = member.id?.value,
                nickname    = member.nickname,
                email       = member.email,
                createdAt   = member.createdAt,
                createdBy   = member.createdBy,
                updatedAt   = member.updatedAt,
                updatedBy   = member.updatedBy,
            )
        }
    }
}