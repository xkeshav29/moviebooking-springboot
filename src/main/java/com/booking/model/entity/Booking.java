package com.booking.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name="booking")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String bookingStatus;

    @NotBlank
    private String paymentStatus;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movieshow_id", nullable = false)
    private Movieshow movieShow;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", createdAt=" + createdAt +
                ", movieShow=" + movieShow +
                '}';
    }
}
