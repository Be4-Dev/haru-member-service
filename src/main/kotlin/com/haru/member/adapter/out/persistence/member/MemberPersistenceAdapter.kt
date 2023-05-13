package com.haru.member.adapter.out.persistence.member

import com.haru.member.application.port.out.WriteMemberPort
import com.haru.member.application.port.out.dto.EntityAudit
import com.haru.member.domain.Member
import org.springframework.stereotype.Component

@Component
class MemberPersistenceAdapter( //@formatter:off
    private val memberRepository: MemberRepository,
) : WriteMemberPort {
    
    override fun saveNew(member: Member, createdBy: String): EntityAudit<Member> {
        val jpaMember       = MemberJpaEntity.from(member, createdBy)
        val savedJpaMember  = memberRepository.save(jpaMember)
        
        return savedJpaMember.toMemberAudit()
    }
    
}