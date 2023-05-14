package com.haru.member.application.port.`in`

import com.haru.member.domain.Member
import com.haru.member.test.TestConstants

//@formatter:off

fun createRegisterMemberCommand(member: Member): RegisterMemberUseCase.Command {
    return RegisterMemberUseCase.Command(
        nickname    = member.nickname,
        email       = member.email,
        password    = member.password,
        createdBy   = TestConstants.createdBy,
    )
}