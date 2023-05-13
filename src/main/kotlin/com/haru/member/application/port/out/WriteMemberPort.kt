package com.haru.member.application.port.out

import com.haru.member.application.port.out.dto.EntityAudit
import com.haru.member.domain.Member

interface WriteMemberPort {
    
    fun saveNew(member: Member, createdBy: String): EntityAudit<Member>
    
}