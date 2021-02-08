package com.app.grip.config;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @CreationTimestamp
    @Column(name = "createDate", nullable = false, updatable = false)
    private Date createDate;

    @CreationTimestamp
    @Column(name = "updateDate")
    private Date updateDate;

    @Column(name = "status", columnDefinition = "varchar(1) default 'Y'")
    private String status;
}