package com.haru.member.domain

data class Member(
    val id: MemberId? = null,
    val nickname: String,
    val email: String,
    val password: String,
) {
    
    data class MemberId(val value: Long)
}