package com.haru.member.application.port.`in`

import com.haru.member.application.port.`in`.dto.MemberDTO

//@formatter:off

interface UpdateProfileUseCase {

    fun updateProfile(command: Command): MemberDTO

    data class Command(
        val memberId    : Long,
        val nickname    : String?,
        val updatedBy   : String,
    )

}