package com.haru.member.application.service

import com.haru.member.application.exception.EmailDuplicatedException
import com.haru.member.application.exception.NicknameDuplicatedException
import com.haru.member.application.port.`in`.RegisterMemberUseCase
import com.haru.member.application.port.`in`.dto.MemberDTO
import com.haru.member.application.port.out.ReadMemberPort
import com.haru.member.application.port.out.WriteMemberPort
import com.haru.member.domain.Member
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

// @formatter:off

@Service
class RegisterMemberService(
    private val writeMemberPort : WriteMemberPort,
    private val readMemberPort  : ReadMemberPort,
) : RegisterMemberUseCase {

    @Transactional
    override fun register(command: RegisterMemberUseCase.Command): MemberDTO {
        if (readMemberPort.existsByNickname(command.nickname))  throw NicknameDuplicatedException(command.nickname)
        if (readMemberPort.existsByEmail(command.email))        throw EmailDuplicatedException(command.email)

        val member = Member(
            nickname    = command.nickname,
            email       = command.email,
            password    = command.password,
            createdBy   = command.createdBy,
        )

        val savedMember = writeMemberPort.saveNew(member)

        return MemberDTO.from(savedMember)
    }

}