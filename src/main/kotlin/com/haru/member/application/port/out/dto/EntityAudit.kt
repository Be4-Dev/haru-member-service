package com.haru.member.application.port.out.dto

data class EntityAudit<T>( //@formatter:off
    val entity  : T,
    val audit   : Audit,
)