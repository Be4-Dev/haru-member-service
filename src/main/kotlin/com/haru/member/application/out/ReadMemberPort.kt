package com.haru.member.application.out

interface ReadMemberPort {
    
    fun existsByNickname(nickname: String): Boolean
    
    fun existsByEmail(email: String): Boolean
    
}