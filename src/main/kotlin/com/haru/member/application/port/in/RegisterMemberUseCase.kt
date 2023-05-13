package com.haru.member.application.port.`in`

import com.haru.member.application.port.out.dto.EntityAudit
import com.haru.member.domain.Member
import java.time.LocalDateTime

interface RegisterMemberUseCase { //@formatter:off
    
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
        val createdAt   : LocalDateTime,
        val createdBy   : String,
        val updatedAt   : LocalDateTime?,
        val updatedBy   : String?,
    )
    
    companion object {
        fun from(entityAudit: EntityAudit<Member>): Result {
            val (member, audit) = entityAudit

            return Result(
                id          = member.id?.value,
                nickname    = member.nickname,
                email       = member.email,
                createdAt   = audit.createdAt,
                createdBy   = audit.createdBy,
                updatedAt   = audit.updatedAt,
                updatedBy   = audit.updatedBy,
            )
        }
    }
    
}