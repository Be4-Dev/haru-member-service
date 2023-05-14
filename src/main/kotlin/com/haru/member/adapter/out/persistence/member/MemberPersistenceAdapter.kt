package com.haru.member.adapter.out.persistence.member

import com.haru.member.application.port.out.WriteMemberPort
import com.haru.member.domain.Member
import org.springframework.stereotype.Component

@Component
class MemberPersistenceAdapter( //@formatter:off
    private val memberRepository: MemberRepository,
) : WriteMemberPort {
    
    override fun saveNew(member: Member): Member {
        val jpaMember       = MemberJpaEntityJpa.from(member)
        val savedJpaMember  = memberRepository.save(jpaMember)
        
        return savedJpaMember.toMember()
    }

    override fun update(member: Member): Member {
        val jpaMember           = MemberJpaEntityJpa.from(member)
        val updatedJpaMember    = memberRepository.save(jpaMember)

        return updatedJpaMember.toMember()
    }
    
}