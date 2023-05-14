package com.haru.member.application.port.`in`

import com.haru.member.test.TestConstants

//@formatter:off

fun createUpdateProfileCommand(
    nickname: String? = null,
): UpdateProfileUseCase.Command {
    return UpdateProfileUseCase.Command(
        memberId    = 1L,
        nickname    = nickname,
        updatedBy   = TestConstants.updatedBy,
    )
}