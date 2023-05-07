package com.haru.member.application.out

import com.haru.member.domain.Member

interface WriteMemberPort {
    fun saveNew(member: Member): Member
}