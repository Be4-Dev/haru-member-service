package com.haru.member.application.`in`

import com.haru.member.domain.Member

interface RegisterMemberUseCase {
    fun register(command: Command): Result
    
    data class Command(
        val nickname:   String,
        val email:      String,
        val password:   String,
    )
    
    data class Result(
        val nickname:   String,
        val email:      String,
    )
    
    companion object {
        fun from(member: Member) = Result(
            nickname    = member.nickname,
            email       = member.email
        )
    }
}