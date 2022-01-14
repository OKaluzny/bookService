package com.hillel.bookservice.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "rewards")
@Data
@NoArgsConstructor
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reward_id", nullable = false)
    private Long rewardId;

    private String name;

    private String description;

    @ManyToOne
    //@ManyToOne(cascade = CascadeType.ALL, fetch = EAGER)
    //@JoinColumn(name = "author_fk", nullable = false)
    private Author author;

    @ManyToOne
    //@ManyToOne(cascade = CascadeType.ALL, fetch = EAGER)
    //@JoinColumn(name = "book_fk", nullable = false)
    private Book book;

    @ManyToOne
    //@ManyToOne(cascade = CascadeType.ALL, fetch = EAGER)
    //@JoinColumn(name = "publisher_fk", nullable = false)
    private Publisher publisher;
}
