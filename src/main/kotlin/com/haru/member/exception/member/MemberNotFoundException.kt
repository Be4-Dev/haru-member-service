package com.haru.member.exception.member

import com.haru.member.global.exception.BusinessException

class MemberNotFoundException private constructor(
    override val message: String,
    override val cause: Throwable? = null,
) : BusinessException(
    errorCode = MemberErrorCode.MEMBER_NOT_FOUND,
    message = message,
    cause = cause,
) {
    constructor (memberId: Long, cause: Throwable? = null) : this(
        message = "현재 멤버를 찾을 수 없습니다. (id: ${memberId})",
        cause = cause,
    )
}