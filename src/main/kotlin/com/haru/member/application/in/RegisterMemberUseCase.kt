package com.haru.member.application.`in`

import com.haru.member.domain.Member

interface RegisterMemberUseCase {
    
    fun register(command: Command): Result
    
    data class Command( // Request DTO
        val nickname:   String,
        val email:      String,
        val password:   String,
    )
    
    data class Result( // Response DTO
        val id:         Long?,
        val nickname:   String,
        val email:      String,
    )
    
    companion object {
        fun from(member: Member) = Result(
            id          = member.id?.value,
            nickname    = member.nickname,
            email       = member.email
        )
    }
    
}