package com.haru.member.adapter.out.persistence.member

import com.haru.member.adapter.out.persistence.config.JpaAuditingConfig
import com.haru.member.domain.createMember
import com.haru.member.test.RepositoryTest
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
        val member = createMember()

        When("회원 정보를 DB에 저장하면") {
            val savedMember = adapterUnderTest.saveNew(member)

            Then("새 회원 정보가 저장된다.") {
                savedMember.id?.value   shouldNotBe     null
                savedMember.email       shouldBe        member.email
                savedMember.nickname    shouldBe        member.nickname
                savedMember.password    shouldBe        member.password
                savedMember.createdAt   shouldNotBe     null
                savedMember.createdBy   shouldNotBe     null
            }
        }
    }

})