package com.bm.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "vendor")
    private String vendor;

    @CreatedDate
    @Column(updatable = false, name = "date_created")
    private Date dateCreated;

    @Column(name = "status")
    private String status;

    @ManyToMany(mappedBy = "devices")
    @JsonIgnore
    @ToString.Exclude
    private Set<Gateway> gateway;


    @PrePersist
    protected void onCreate() {
        dateCreated = new Date();
    }
}