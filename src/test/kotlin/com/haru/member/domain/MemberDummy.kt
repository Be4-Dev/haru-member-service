package com.haru.member.domain

import com.haru.member.test.TestConstants
import java.time.LocalDateTime

//@formatter:off
fun createMember(
    id          : Long      = 1L,
    nickname    : String    = "테스터",
    email       : String    = "tester@gmail.com",
    password    : String    = "1111",
    createdBy   : String    = TestConstants.createdBy,
    updatedBy   : String?   = null,
): Member {
    val member = Member(
        id          = Member.MemberId(value = id),
        nickname    = nickname,
        email       = email,
        password    = password,
        createdBy   = createdBy,
    )
    member.createdAt = LocalDateTime.now()
    member.updatedBy = updatedBy

    return member
}