package com.haru.member.application.service

import com.haru.member.application.port.`in`.UpdateProfileUseCase
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
    override fun updateProfile(command: UpdateProfileUseCase.Command): UpdateProfileUseCase.Result {
        val member          = readMemberPort.findById(command.memberId)
        member.updateProfile(command.nickname)

        val updatedMember   = writeMemberPort.update(member)

        return UpdateProfileUseCase.Result.from(updatedMember)
    }

}