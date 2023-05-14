package com.haru.member.adapter.out.persistence

import com.haru.member.domain.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime

//@formatter:off

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class JpaBaseEntity(
    @Column(updatable = false)
    @Comment("생성자")
    val createdBy: String,
) : Serializable {

    @Column(updatable = false)
    @Comment("생성일")
    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @Column(insertable = false)
    @Comment("수정일")
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null

    @Column(insertable = false)
    @Comment("수정자")
    var updatedBy: String? = null

    protected fun <T : BaseEntity> setAudit(entity: T): T {
        entity.createdAt = createdAt
        entity.createdBy = createdBy
        entity.updatedAt = updatedAt
        entity.updatedBy = updatedBy

        return entity
    }

}