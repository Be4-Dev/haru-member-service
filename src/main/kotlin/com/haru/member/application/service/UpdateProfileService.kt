package com.haru.member.application.service

import com.haru.member.application.port.`in`.UpdateProfileUseCase
import com.haru.member.application.port.`in`.dto.MemberDTO
import com.haru.member.application.port.out.ReadMemberPort
import com.haru.member.application.port.out.WriteMemberPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

//@formatter:off

@Service
@Transactional(readOnly = true)
class UpdateProfileService(
    private val readMemberPort  : ReadMemberPort,
    private val writeMemberPort : WriteMemberPort,
) : UpdateProfileUseCase {

    @Transactional
    override fun updateProfile(command: UpdateProfileUseCase.Command): MemberDTO {
        val member = readMemberPort.findById(command.memberId)
        member.updateProfile(command.nickname)
        member.updatedBy = command.updatedBy

        val updatedMember = writeMemberPort.update(member)

        return MemberDTO.from(updatedMember)
    }

}