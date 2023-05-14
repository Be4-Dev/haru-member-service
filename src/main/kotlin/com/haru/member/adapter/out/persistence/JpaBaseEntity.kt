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
    createdBy: String,
) : Serializable {

    @Column(nullable = false, updatable = false)
    @Comment("생성자")
    var createdBy: String = createdBy
        private set

    @Column(nullable = false, updatable = false)
    @Comment("생성일")
    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.MIN
        private set

    @Column(insertable = false)
    @Comment("수정일")
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
        private set

    @Column(insertable = false)
    @Comment("수정자")
    var updatedBy: String = createdBy
        private set

    protected fun <T : BaseEntity> setAuditTo(entity: T): T {
        entity.createdAt = createdAt
        entity.createdBy = createdBy
        entity.updatedAt = updatedAt
        entity.updatedBy = updatedBy

        return entity
    }

    protected fun <T : BaseEntity> extractAuditFrom(entity: T) {
        createdAt = entity.createdAt ?: createdAt
        createdBy = entity.createdBy
        updatedAt = entity.updatedAt
        updatedBy = entity.updatedBy ?: updatedBy
    }

}