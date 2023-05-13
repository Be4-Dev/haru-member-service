package com.haru.member.adapter.out.persistence.member

import com.haru.member.application.port.out.ReadMemberPort
import org.springframework.stereotype.Component

@Component
class MemberReadAdapter(
    private val memberRepository: MemberRepository,
): ReadMemberPort {
    
    override fun existsByNickname(nickname: String): Boolean {
        return memberRepository.existsByNickname(nickname)
    }
    
    override fun existsByEmail(email: String): Boolean {
        return memberRepository.existsByEmail(email)
    }
    
}