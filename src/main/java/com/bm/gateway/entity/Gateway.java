package com.bm.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "gateway")
public class Gateway {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "ipv4")
    private String ipv4;

    @Column(name = "valid")
    @ColumnDefault("false")
    @JsonIgnore
    private boolean valid;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "devices_gateway",
            joinColumns = {@JoinColumn(name = "gateway_id")},
            inverseJoinColumns = {@JoinColumn(name = "device_id")}
    )
    @ToString.Exclude
    private Set<Device> devices;

}