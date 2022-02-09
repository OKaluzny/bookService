package com.hillel.bookservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "authors")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(nullable = false)
    private String firstName;

    private String lastName;

    private String country;

    //@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    //@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Set<Book> books = new HashSet<>();

    //@JsonIgnore
    //@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Set<Reward> rewards = new HashSet<>();

    @ManyToOne
    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@ManyToOne
    //@JoinColumn(name = "publisher_id")
    @JsonIgnore
    private Publisher publisher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Author author = (Author) o;
        return Objects.equals(authorId, author.authorId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
