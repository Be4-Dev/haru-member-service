package com.haru.member.domain

class Member( //@formatter:off
    val id          : MemberId? = null,
    var nickname    : String,
    val email       : String,
    val password    : String,
    createdBy       : String,
) : BaseEntity(createdBy = createdBy) {

    fun updateProfile(nickname: String?) {
        this.nickname = nickname ?: this.nickname
    }
    
    data class MemberId(val value: Long)
}