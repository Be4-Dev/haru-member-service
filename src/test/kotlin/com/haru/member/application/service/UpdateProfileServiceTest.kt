package com.haru.member.application.service

import com.haru.member.application.port.`in`.createUpdateProfileCommand
import com.haru.member.application.port.out.ReadMemberPort
import com.haru.member.application.port.out.WriteMemberPort
import com.haru.member.application.port.out.mockFindByIdWillSuccess
import com.haru.member.application.port.out.mockUpdateWillSuccess
import com.haru.member.domain.createMember
import com.haru.member.test.ServiceTest
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.mockk
import io.mockk.verify

@ServiceTest
internal class UpdateProfileServiceTest : BehaviorSpec({

    val readMemberPort  = mockk<ReadMemberPort>()
    val writeMemberPort = mockk<WriteMemberPort>()

    val updateProfileService = UpdateProfileService(
        readMemberPort = readMemberPort,
        writeMemberPort = writeMemberPort,
    )

    Given("변경된 회원 프로필 정보가 주어졌을 때") {
        val command = createUpdateProfileCommand()
        val member  = createMember(id = command.memberId)

        readMemberPort.mockFindByIdWillSuccess(member = member)
        writeMemberPort.mockUpdateWillSuccess(
            member      = member,
            nickname    = command.nickname,
            updatedBy   = command.updatedBy,
        )

        When("회원 프로필 수정 서비스를 호출하면") {
            val result = updateProfileService.updateProfile(command)

            Then("회원 정보 수정 메서드를 호출해야 한다.") {
                verify(exactly = 1) { writeMemberPort.update(any()) }
            }
        }
    }

})