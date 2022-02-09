package com.hillel.bookservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "publishers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "publisher_id", nullable = false)
    private Long publisherId;

    private String name;
    private String country;
    private String city;
    private String address;

    //@JsonIgnore
    //@OneToMany(mappedBy = "publisher")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Author> authors = new HashSet<>();

    //@JsonIgnore
    //@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private List<Reward> rewards = new ArrayList<>();

}
