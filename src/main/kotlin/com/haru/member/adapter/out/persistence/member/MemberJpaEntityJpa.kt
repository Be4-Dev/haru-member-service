package com.haru.member.adapter.out.persistence.member

import com.haru.member.adapter.out.persistence.JpaBaseEntity
import com.haru.member.domain.Member
import jakarta.persistence.*

//@formatter:off

@Entity
@Table(name = "member")
class MemberJpaEntityJpa(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id          : Long?,
    val nickname    : String,
    val email       : String,
    val password    : String,
    createdBy       : String,
) : JpaBaseEntity(createdBy = createdBy) {

    fun toMember(): Member {
        val member = Member(
            id          = id?.let { Member.MemberId(it) },
            nickname    = nickname,
            email       = email,
            password    = password,
            createdBy   = createdBy,
        )

        return setAudit(member)
    }
    
    companion object {
        fun from(member: Member): MemberJpaEntityJpa {
            return MemberJpaEntityJpa(
                id          = member.id?.value,
                nickname    = member.nickname,
                email       = member.email,
                password    = member.password,
                createdBy   = member.createdBy,
            )
        }
    }
    
}