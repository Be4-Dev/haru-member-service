package com.haru.member.adapter.out.persistence.member

import com.haru.member.test.RepositoryTest
import com.haru.member.adapter.out.persistence.config.JpaAuditingConfig
import com.haru.member.domain.MemberDummy
import com.haru.member.test.TestConstants
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.context.annotation.Import

@RepositoryTest
@Import(value = [MemberPersistenceAdapter::class, JpaAuditingConfig::class])
internal class MemberPersistenceAdapterTest(
    private val adapterUnderTest: MemberPersistenceAdapter,
) : ExpectSpec({

    context("회원 Command") { //@formatter:off
        val member = MemberDummy.create()

        val (savedMember, audit) = adapterUnderTest.saveNew(member, TestConstants.createdBy)

        expect("새 회원 정보가 저장됩니다.") {
            savedMember.id          shouldNotBe     null
            savedMember.email       shouldBe        member.email
            savedMember.nickname    shouldBe        member.nickname
            savedMember.password    shouldBe        member.password
            audit.createdAt         shouldNotBe     null
            audit.createdBy         shouldNotBe     null
        }
    }

})