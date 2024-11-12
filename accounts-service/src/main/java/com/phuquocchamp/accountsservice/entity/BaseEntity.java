package com.phuquocchamp.accountsservice.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class BaseEntity {

    @CreatedDate
    @Column(updatable=false)
    private LocalDateTime createAt;

    @CreatedBy
    @Column(updatable=false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable=false)
    private LocalDateTime updateAt;

    @LastModifiedBy
    @Column(insertable=false)
    private String updatedBy;
}
