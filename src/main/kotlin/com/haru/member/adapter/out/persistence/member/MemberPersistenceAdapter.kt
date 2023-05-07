package com.haru.member.adapter.out.persistence.member

import com.haru.member.application.out.WriteMemberPort
import com.haru.member.domain.Member
import org.springframework.stereotype.Component

@Component
class MemberPersistenceAdapter(
    private val memberRepository: MemberRepository,
) : WriteMemberPort {
    
    override fun saveNew(member: Member): Member {
        val jpaMember       = MemberJpaEntity.from(member)
        val savedJpaMember  = memberRepository.save(jpaMember)
        
        return savedJpaMember.toDomainEntity()
    }
    
}