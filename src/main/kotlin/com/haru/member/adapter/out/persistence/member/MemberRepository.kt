package com.haru.member.adapter.out.persistence.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//@formatter:off

@Repository
interface MemberRepository : JpaRepository<MemberJpaEntityJpa, Long> {
    
    fun existsByNickname(nickname: String)  : Boolean
    
    fun existsByEmail(email: String)        : Boolean
    
}