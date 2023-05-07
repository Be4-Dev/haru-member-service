package com.haru.member.application.service

import com.haru.member.application.`in`.RegisterMemberUseCase
import com.haru.member.application.out.ReadMemberPort
import com.haru.member.application.out.WriteMemberPort
import com.haru.member.domain.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegisterMemberService(
    private val writeMemberPort: WriteMemberPort,
    private val readMemberPort: ReadMemberPort,
): RegisterMemberUseCase {
    @Transactional
    override fun register(command: RegisterMemberUseCase.Command): RegisterMemberUseCase.Result {
        if (readMemberPort.existsByNickname(command.nickname))  throw Exception("닉네임이 이미 존재합니다.")
        if (readMemberPort.existsByEmail(command.email))        throw Exception("이메일이 이미 존재합니다.")
        
        val member = Member(
            nickname    = command.nickname,
            email       = command.email,
            password    = command.password,
        )
        
        val savedMember = writeMemberPort.saveNew(member)
        
        return RegisterMemberUseCase.from(savedMember)
    }
}