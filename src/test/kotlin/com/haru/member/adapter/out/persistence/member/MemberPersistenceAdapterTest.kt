package com.haru.member.adapter.out.persistence.member

import com.haru.member.adapter.out.persistence.config.JpaAuditingConfig
import com.haru.member.domain.MemberDummy
import com.haru.member.test.RepositoryTest
import com.haru.member.test.TestConstants
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.context.annotation.Import

@RepositoryTest
@Import(value = [MemberPersistenceAdapter::class, JpaAuditingConfig::class])
internal class MemberPersistenceAdapterTest(
    private val adapterUnderTest: MemberPersistenceAdapter,
) : BehaviorSpec({

    Given("새로운 회원 정보가 주어졌을 때") { //@formatter:off
        val member = MemberDummy.create()

        When("회원 정보를 DB에 저장하면") {
            val (savedMember, audit) = adapterUnderTest.saveNew(member, TestConstants.createdBy)

            Then("새 회원 정보가 저장된다.") {
                savedMember.id?.value   shouldNotBe     null
                savedMember.email       shouldBe        member.email
                savedMember.nickname    shouldBe        member.nickname
                savedMember.password    shouldBe        member.password
                audit.createdAt         shouldNotBe     null
                audit.createdBy         shouldNotBe     null
            }
        }
    }

})