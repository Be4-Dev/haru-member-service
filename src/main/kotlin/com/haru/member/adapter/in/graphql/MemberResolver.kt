package com.haru.member.adapter.`in`.graphql

import com.haru.member.application.port.`in`.RegisterMemberUseCase
import com.haru.member.application.port.`in`.UpdateProfileUseCase
import com.haru.member.application.port.`in`.dto.MemberDTO
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class MemberResolver(
    private val registerMemberUseCase   : RegisterMemberUseCase,
    private val updateProfileUseCase    : UpdateProfileUseCase,
) {

    @MutationMapping
    fun registerMember(@Argument command: RegisterMemberUseCase.Command): MemberDTO {
        return registerMemberUseCase.register(command)
    }

    @MutationMapping
    fun updateMemberProfile(@Argument command: UpdateProfileUseCase.Command): MemberDTO {
        return updateProfileUseCase.updateProfile(command)
    }
    
}