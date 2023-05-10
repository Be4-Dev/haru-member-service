package com.haru.member.adapter.out.persistence.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<MemberJpaEntity, Long> {
    
    fun existsByNickname(nickname: String): Boolean
    
    fun existsByEmail(email: String): Boolean
    
}