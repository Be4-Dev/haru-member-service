package com.haru.member.adapter.out.persistence.member

import com.haru.member.application.port.out.WriteMemberPort
import com.haru.member.domain.Member
import org.springframework.stereotype.Component

//@formatter:off

@Component
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
) : WriteMemberPort {
    
    override fun saveNew(member: Member): Member {
        val jpaMember       = MemberJpaEntity.from(member)
        val savedJpaMember  = memberRepository.save(jpaMember)
        
        return savedJpaMember.toMember()
    }

    override fun update(member: Member): Member {
        val jpaMember           = MemberJpaEntity.from(member)
        val updatedJpaMember    = memberRepository.saveAndFlush(jpaMember)

        return updatedJpaMember.toMember()
    }
    
}