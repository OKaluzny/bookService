package com.hillel.bookservice.repository;

import com.hillel.bookservice.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a.firstName,a.lastName " +
            "from Author a " +
            "inner join Publisher p " +
            "on a.authorId = p.publisherId " +
            "where p.publisherId = ?1")
    Author findAuthorByPublisherPublisherId(Long id);

    Author getAuthorByPublisherPublisherId(Long id);

}
