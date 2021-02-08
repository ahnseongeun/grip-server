package com.app.grip.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreationTimestamp
    @Column(name = "createDate", nullable = false, updatable = false)
    private Date createDate;

    @CreationTimestamp
    @Column(name = "updateDate")
    private Date updateDate;

    @Column(name = "status",length = 1)
    private String status = "Y";
}