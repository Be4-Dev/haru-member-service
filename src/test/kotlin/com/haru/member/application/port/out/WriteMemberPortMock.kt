package com.haru.member.application.port.out

import com.haru.member.domain.Member
import io.mockk.every

//@formatter:off

/* saveNew() */
fun WriteMemberPort.mockSaveNewWillSuccess(
    member : Member,
) {
    every { saveNew(any()) } answers { member }
}

/* update() */
fun WriteMemberPort.mockUpdateWillSuccess(
    member      : Member,
    nickname    : String?,
    updatedBy   : String,
) {
    member.updateProfile(nickname = nickname)
    member.updatedBy = updatedBy

    every { update(any()) } answers { member }
}