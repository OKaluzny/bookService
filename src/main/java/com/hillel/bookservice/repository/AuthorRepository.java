package com.hillel.bookservice.repository;

import com.hillel.bookservice.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
