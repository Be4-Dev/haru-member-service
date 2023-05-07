package com.haru.member.adapter.out.persistence.member

import com.haru.member.domain.Member
import jakarta.persistence.*

@Entity
@Table(name = "member")
class MemberJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:         Long?,
    val nickname:   String,
    val email:      String,
    val password:   String,
) {
    
    companion object {
        fun from(member: Member): MemberJpaEntity {
            return MemberJpaEntity(
                id          = member.id?.value,
                nickname    = member.nickname,
                email       = member.email,
                password    = member.password,
            )
        }
    }
    
}

fun MemberJpaEntity.toDomainEntity(): Member {
    return Member(
        id          = id?.let { Member.MemberId(it) },
        nickname    = nickname,
        email       = email,
        password    = password,
    )
}