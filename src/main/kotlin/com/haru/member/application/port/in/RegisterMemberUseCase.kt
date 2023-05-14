package com.haru.member.application.port.`in`

import com.haru.member.application.port.`in`.dto.MemberDTO

//@formatter:off

interface RegisterMemberUseCase {
    
    fun register(command: Command): MemberDTO

    data class Command(
        val nickname    : String,
        val email       : String,
        val password    : String,
        val createdBy   : String,
    )
    
}