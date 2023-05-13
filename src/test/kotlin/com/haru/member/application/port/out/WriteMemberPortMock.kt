package com.haru.member.application.port.out

import com.haru.member.application.port.out.dto.Audit
import com.haru.member.application.port.out.dto.EntityAudit
import com.haru.member.domain.Member
import com.haru.member.test.TestConstants
import io.mockk.every
import java.time.LocalDateTime

//@formatter:off

/* saveNew() */
fun WriteMemberPort.mockSaveNewWillSuccess(
    member      : Member,
    createdBy   : String = TestConstants.createdBy,
) {
    val audit       = Audit(createdAt = LocalDateTime.now(), createdBy = createdBy)
    val entityAudit = EntityAudit(entity = member, audit = audit)

    every { saveNew(any(), any()) } answers { entityAudit }
}