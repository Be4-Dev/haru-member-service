package com.haru.member.application.port.`in`

import com.haru.member.domain.Member
import java.time.LocalDateTime

//@formatter:off

interface RegisterMemberUseCase {
    
    fun register(command: Command): Result
    
    data class Command(
        val nickname    : String,
        val email       : String,
        val password    : String,
        val createdBy   : String,
    )
    
    data class Result(
        val id          : Long?,
        val nickname    : String,
        val email       : String,
        val createdAt   : LocalDateTime?,
        val createdBy   : String,
        val updatedAt   : LocalDateTime?,
        val updatedBy   : String?,
    )
    
    companion object {
        fun from(member: Member): Result {
            return Result(
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