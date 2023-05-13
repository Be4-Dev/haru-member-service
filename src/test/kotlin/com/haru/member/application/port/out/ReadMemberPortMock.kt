package com.haru.member.application.port.out

import io.mockk.every

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