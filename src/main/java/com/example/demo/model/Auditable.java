package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements Serializable {

    @CreatedBy
    @Column(name = "CREATED_BY", length = 24)
    protected String createdBy;

    @LastModifiedBy
    @Column(name = "UPDATED_BY", length = 24)
    protected String updatedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    protected Date createdTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME")
    protected Date updatedTime;

}
