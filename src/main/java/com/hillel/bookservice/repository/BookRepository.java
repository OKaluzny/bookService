package com.hillel.bookservice.repository;

import com.hillel.bookservice.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
