package com.hillel.bookservice.repository;

import com.hillel.bookservice.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    //@Query("select p.name from Publisher p inner join Author a on p.publisherId = a.publisher.publisherId where a.authorId = ?1")
    @Query(value = "Select p.name from publishers p inner join authors a on p.publisher_id = a.publisher_id where a.author_id = ?1", nativeQuery = true)
    Publisher findPublisher(Long id);

    Optional<Publisher> findByName(String name);

    //Publisher getPublisherByAuthorId(Long id);
}
