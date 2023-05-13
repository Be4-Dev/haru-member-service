package com.haru.member.domain

//@formatter:off
class MemberDummy {

    companion object {
        fun create(
            id:         Long    = 1L,
            nickname:   String  = "테스터",
            email:      String  = "tester@gmail.com",
            password:   String  = "1111",
        ): Member {
            return Member(
                id          = Member.MemberId(value = id),
                nickname    = nickname,
                email       = email,
                password    = password,
            )
        }
    }

}