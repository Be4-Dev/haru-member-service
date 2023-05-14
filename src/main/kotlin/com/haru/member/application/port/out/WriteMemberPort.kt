package com.haru.member.application.port.out

import com.haru.member.domain.Member

interface WriteMemberPort {
    
    fun saveNew(member: Member) : Member

    fun update(member: Member)  : Member

}