package com.haru.member.adapter.out.persistence.member

import com.haru.member.adapter.out.persistence.BaseEntity
import com.haru.member.application.port.out.dto.EntityAudit
import com.haru.member.domain.Member
import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberJpaEntity( //@formatter:off
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id          : Long?,
    val nickname    : String,
    val email       : String,
    val password    : String,
    createdBy       : String,
    updatedBy       : String? = null,
) : BaseEntity(createdBy = createdBy, updatedBy = updatedBy) {

    fun toMemberAudit(): EntityAudit<Member> {
        val member = Member(
            id          = id?.let { Member.MemberId(it) },
            nickname    = nickname,
            email       = email,
            password    = password,
        )

        return getEntityAudit(member)
    }
    
    companion object {
        fun from(member: Member, createdBy: String): MemberJpaEntity {
            return MemberJpaEntity(
                id          = member.id?.value,
                nickname    = member.nickname,
                email       = member.email,
                password    = member.password,
                createdBy   = createdBy,
            )
        }
    }
    
}