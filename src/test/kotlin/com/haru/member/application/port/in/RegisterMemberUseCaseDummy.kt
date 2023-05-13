package com.haru.member.application.port.`in`

import com.haru.member.domain.Member
import com.haru.member.test.TestConstants

class RegisterMemberUseCaseDummy { //@formatter:off

    companion object {
        fun createCommand(member: Member): RegisterMemberUseCase.Command {
            return RegisterMemberUseCase.Command(
                nickname    = member.nickname,
                email       = member.email,
                password    = member.password,
                createdBy   = TestConstants.createdBy,
            )
        }
    }

}