package com.haru.member.application.service

import com.haru.member.application.port.`in`.createRegisterMemberCommand
import com.haru.member.application.port.out.*
import com.haru.member.domain.createMember
import com.haru.member.test.ServiceTest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

@ServiceTest
internal class RegisterMemberServiceTest : BehaviorSpec({ //@formatter:off

    val writeMemberPort = mockk<WriteMemberPort>()
    val readMemberPort  = mockk<ReadMemberPort>()

    val registerMemberService = RegisterMemberService(
        writeMemberPort = writeMemberPort,
        readMemberPort  = readMemberPort
    )
    
    Given("새로운 회원 정보가 주어졌을 때") {
        val member  = createMember()
        val command = createRegisterMemberCommand(member)

        readMemberPort.mockNicknameWillNotExists()
        readMemberPort.mockEmailWillNotExists()
        writeMemberPort.mockSaveNewWillSuccess(member)

        When("회원 가입 서비스를 호출하면") {
            val result = registerMemberService.register(command)

            Then("저장된 회원 정보가 반환된다.") {
                result.email        shouldBe    command.email
                result.nickname     shouldBe    command.nickname
            }
        }
    }

    Given("중복된 닉네임이 존재할 경우") {
        val member  = createMember()
        val command = createRegisterMemberCommand(member)

        readMemberPort.mockNicknameWillExists()
        readMemberPort.mockEmailWillNotExists()

        When("회원 가입 서비스를 호출하면") {
            Then("예외를 던진다.") {
                shouldThrow<Exception> { registerMemberService.register(command) }
            }
        }
    }

    Given("중복된 이메일이 존재할 경우") {
        val member  = createMember()
        val command = createRegisterMemberCommand(member)

        readMemberPort.mockNicknameWillNotExists()
        readMemberPort.mockEmailWillExists()

        When("회원 가입 서비스를 호출하면") {
            Then("예외를 던진다.") {
                shouldThrow<Exception> { registerMemberService.register(command) }
            }
        }
    }
    
})