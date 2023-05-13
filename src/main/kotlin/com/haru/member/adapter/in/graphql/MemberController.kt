package com.haru.member.adapter.`in`.graphql

import com.haru.member.application.port.`in`.RegisterMemberUseCase
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class MemberController(
    private val registerMemberUseCase: RegisterMemberUseCase,
) {
    
    @MutationMapping
    fun registerMember(@Argument command: RegisterMemberUseCase.Command): RegisterMemberUseCase.Result {
        return registerMemberUseCase.register(command)
    }
    
}