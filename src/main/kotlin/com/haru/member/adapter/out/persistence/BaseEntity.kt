package com.haru.member.adapter.out.persistence

import com.haru.member.application.port.out.dto.Audit
import com.haru.member.application.port.out.dto.EntityAudit
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @Column(updatable = false)
    @Comment("생성자")
    val createdBy: String,

    @Column(insertable = false)
    @Comment("수정자")
    val updatedBy: String? = null,
) : Serializable {

    @Column(updatable = false)
    @Comment("생성일")
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @Column(insertable = false)
    @Comment("수정일")
    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    protected fun <T> getEntityAudit(entity: T): EntityAudit<T> { //@formatter:off
        return EntityAudit(
            entity  = entity,
            audit   = Audit(
                createdAt = createdAt,
                updatedAt = updatedAt,
                createdBy = createdBy,
                updatedBy = updatedBy
            )
        )
    } //@formatter:on

}