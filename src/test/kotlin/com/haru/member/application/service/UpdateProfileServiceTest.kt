package com.haru.member.application.service

import com.haru.member.application.port.`in`.createUpdateProfileCommand
import com.haru.member.application.port.out.ReadMemberPort
import com.haru.member.application.port.out.WriteMemberPort
import com.haru.member.application.port.out.mockFindByIdWillSuccess
import com.haru.member.application.port.out.mockUpdateWillSuccess
import com.haru.member.domain.createMember
import com.haru.member.test.ServiceTest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.mockk
import io.mockk.verify

@ServiceTest
internal class UpdateProfileServiceTest : BehaviorSpec({

    val readMemberPort  = mockk<ReadMemberPort>()
    val writeMemberPort = mockk<WriteMemberPort>()

    val updateProfileService = UpdateProfileService(
        readMemberPort  = readMemberPort,
        writeMemberPort = writeMemberPort,
    )

    Given("회원 닉네임 변경 정보가 주어졌을 때") {
        val nickname = "변경된닉네임"
        
        val command = createUpdateProfileCommand(nickname = nickname)
        val memberDummy  = createMember(id = command.memberId)

        readMemberPort.mockFindByIdWillSuccess(member = memberDummy)
        writeMemberPort.mockUpdateWillSuccess(
            member      = memberDummy,
            nickname    = command.nickname,
            updatedBy   = command.updatedBy,
        )

        When("회원 프로필 수정 서비스를 호출하면") {
            val result = updateProfileService.updateProfile(command)

            Then("회원 정보 수정 메서드를 호출해야 한다.") {
                verify(exactly = 1) { writeMemberPort.update(any()) }
            }

            Then("올바른 회원 정보가 저장돼야 한다.") {
                if (command.nickname != null)
                result.nickname     shouldBe    command.nickname
                result.updatedBy    shouldBe    command.updatedBy
                result.updatedAt    shouldNotBe null
            }
        }
    }

})