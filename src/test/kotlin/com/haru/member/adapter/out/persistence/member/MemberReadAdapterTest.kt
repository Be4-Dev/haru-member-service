package com.haru.member.adapter.out.persistence.member

import com.haru.member.adapter.out.persistence.config.JpaAuditingConfig
import com.haru.member.domain.createMember
import com.haru.member.test.RepositoryTest
import com.haru.member.test.TestConstants
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.context.annotation.Import

//@formatter:off
@RepositoryTest
@Import(value = [MemberReadAdapter::class, MemberPersistenceAdapter::class, JpaAuditingConfig::class])
internal class MemberReadAdapterTest(
    private val adapterUnderTest            : MemberReadAdapter,
    private val memberPersistenceAdapter    : MemberPersistenceAdapter,
) : BehaviorSpec({

    Given("1건의 회원 레코드가 주어졌을 때") {
        val member      = createMember(createdBy = TestConstants.createdBy)

        memberPersistenceAdapter.saveNew(member)

        When("일치하는 닉네임이 있으면") {
            val isExist = adapterUnderTest.existsByNickname(member.nickname)

            Then("true를 반환한다.") {
                isExist shouldBe true
            }
        }

        When("일치하는 닉네임이 없으면") {
            val isExist = adapterUnderTest.existsByNickname(member.nickname + "*")

            Then("false를 반환한다.") {
                isExist shouldBe false
            }
        }

        When("일치하는 이메일이 있으면") {
            val isExist = adapterUnderTest.existsByEmail(member.email)

            Then("true를 반환한다.") {
                isExist shouldBe true
            }
        }

        When("일치하는 이메일이 없으면") {
            val isExist = adapterUnderTest.existsByEmail(member.email + "*")

            Then("false를 반환한다.") {
                isExist shouldBe false
            }
        }
    }

})