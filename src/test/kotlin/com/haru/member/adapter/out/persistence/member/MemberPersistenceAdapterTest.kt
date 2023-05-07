package com.haru.member.adapter.out.persistence.member

import com.haru.member.domain.Member
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@Import(MemberPersistenceAdapter::class)
@ActiveProfiles("local")
class MemberPersistenceAdapterTest(
    @Autowired private val adapterUnderTest:   MemberPersistenceAdapter,
) {

    @Test
    fun `JPA 회원 등록 테스트`() {
        // Given
        val member      = givenMember()
    
        // When
        val savedMember = adapterUnderTest.saveNew(member)
    
        // Then
        assertAll(
            "JPA로 저장 후 반환 받은 회원 정보의 모든 속성을 검사합니다.",
            { assertNotNull(savedMember.id) },
            { assertEquals(member.nickname, savedMember.nickname) },
            { assertEquals(member.email,    savedMember.email) },
            { assertEquals(member.password, savedMember.password) },
        )
    }
    
    /* Test Objects */
    private fun givenMember(): Member {
        val nickname    = "테스터"
        val email       = "tester@gmail.com"
        val password    = "1234"
    
        return Member(nickname = nickname, email = email, password = password)
    }
}