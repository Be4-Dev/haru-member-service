package com.haru.member.application.port.out

import com.haru.member.domain.Member
import io.mockk.every

/* findById() */
fun ReadMemberPort.mockFindByIdWillSuccess(member: Member) {
    every { findById(any()) } answers { member }
}

/* existsByNickname() */
fun ReadMemberPort.mockNicknameWillNotExists() {
    every { existsByNickname(any()) } answers { false }
}

fun ReadMemberPort.mockNicknameWillExists() {
    every { existsByNickname(any()) } answers { true }
}

/* existsByEmail() */
fun ReadMemberPort.mockEmailWillNotExists() {
    every { existsByEmail(any()) } answers { false }
}

fun ReadMemberPort.mockEmailWillExists() {
    every { existsByEmail(any()) } answers { true }
}