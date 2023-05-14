package com.haru.member.adapter.out.persistence.member

import com.haru.member.application.port.out.ReadMemberPort
import com.haru.member.domain.Member
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class MemberReadAdapter(
    private val memberRepository: MemberRepository,
): ReadMemberPort {

    override fun findById(id: Long): Member {
        val jpaMember = memberRepository.findByIdOrNull(id) ?: throw Exception("Member(id: $id)를 찾을 수 없습니다.")
        return jpaMember.toMember()
    }

    override fun existsByNickname(nickname: String): Boolean {
        return memberRepository.existsByNickname(nickname)
    }
    
    override fun existsByEmail(email: String): Boolean {
        return memberRepository.existsByEmail(email)
    }
    
}