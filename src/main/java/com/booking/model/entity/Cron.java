package com.booking.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "cron")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Cron {

    @Id
    private String cronName;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastExecutionTime;
}
