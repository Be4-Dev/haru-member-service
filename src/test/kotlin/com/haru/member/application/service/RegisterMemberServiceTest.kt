package com.haru.member.application.service

import com.haru.member.application.`in`.RegisterMemberUseCase
import com.haru.member.application.out.ReadMemberPort
import com.haru.member.application.out.WriteMemberPort
import com.haru.member.domain.Member
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertFailsWith

@ExtendWith(SpringExtension::class)
class RegisterMemberServiceTest {
    
    @Mock
    private lateinit var writeMemberPort: WriteMemberPort
    
    @Mock
    private lateinit var readMemberPort: ReadMemberPort
    
    @InjectMocks
    private lateinit var registerMemberService: RegisterMemberService

    @Test
    fun `회원 등록 성공 시 반환 값 검사`() {
        // Given
        val member  = givenMember()
        val command = givenRegisterCommand(member)
    
        `given nickname will not exist`(member)
        `given email will not exist`(member)
        `given save new function will succeed`(member)
        
        // When
        val result  = registerMemberService.register(command)
        
        // Then
        assertAll(
            "회원 등록 유스케이스의 응답 결과에 포함된 모든 속성을 검사합니다.",
            { assertEquals(member.nickname, result.nickname) },
            { assertEquals(member.email, result.email) },
        )
    
        then(readMemberPort).should().existsByNickname(member.nickname)
        then(readMemberPort).should().existsByEmail(member.email)
        then(writeMemberPort).should().saveNew(member)
    }
    
    @Test
    fun `닉네임 중복 시 예외처리 검사`() {
        // Given
        val member  = givenMember()
        val command = givenRegisterCommand(member)
        
        `given nickname will exist`(member)
        
        // When, Then
        assertFailsWith<Exception> { registerMemberService.register(command) }
    }
    
    @Test
    fun `이메일 중복 시 예외처리 검사`() {
        // Given
        val member  = givenMember()
        val command = givenRegisterCommand(member)
        
        `given email will exist`(member)
        
        // When, Then
        assertFailsWith<Exception> { registerMemberService.register(command) }
    }
    
    /* Test Objects */
    private fun givenMember(): Member {
        val nickname    = "테스터"
        val email       = "tester@gmail.com"
        val password    = "1234"
    
        return Member(nickname = nickname, email = email, password = password)
    }
    private fun givenRegisterCommand(member: Member): RegisterMemberUseCase.Command {
        return RegisterMemberUseCase.Command(
            nickname    = member.nickname,
            email       = member.email,
            password    = member.password
        )
    }
    
    /* Test Doubles : Mock */
    private fun `given nickname will exist`(member: Member) {
        given(readMemberPort.existsByNickname(member.nickname)).willReturn(true)
    }
    private fun `given nickname will not exist`(member: Member) {
        given(readMemberPort.existsByNickname(member.nickname)).willReturn(false)
    }
    
    private fun `given email will exist`(member: Member) {
        given(readMemberPort.existsByEmail(member.email)).willReturn(true)
    }
    private fun `given email will not exist`(member: Member) {
        given(readMemberPort.existsByEmail(member.email)).willReturn(false)
    }
    
    private fun `given save new function will succeed`(member: Member) {
        given(writeMemberPort.saveNew(member)).willReturn(member)
    }
    
}