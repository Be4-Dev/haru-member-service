package com.haru.member.application.port.out

import com.haru.member.domain.Member

//@formatter:off

interface ReadMemberPort {

    fun findById(id: Long)                  : Member

    fun existsByNickname(nickname: String)  : Boolean
    
    fun existsByEmail(email: String)        : Boolean
    
}