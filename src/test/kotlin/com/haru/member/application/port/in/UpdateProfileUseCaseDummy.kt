package com.haru.member.application.port.`in`

import com.haru.member.test.TestConstants

//@formatter:off

fun createUpdateProfileCommand(): UpdateProfileUseCase.Command {
    return UpdateProfileUseCase.Command(
        memberId    = 1L,
        nickname    = "변경된닉네임",
        updatedBy   = TestConstants.updatedBy,
    )
}