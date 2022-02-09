package com.hillel.bookservice.service;

import com.hillel.bookservice.domain.Author;
import com.hillel.bookservice.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Author saveBookAuthor(Author requestForSave) {
        log.debug("saveBookAuthor() - start: requestForSave = {}", requestForSave);

        var author = authorRepository.save(requestForSave);
        log.debug("saveBookAuthor() - end: author = {}", author);
        return author;
    }

    public Author findBookAuthorById(Long id) {
        log.error("findBookAuthorById() - start: id = {}", id);
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id = " + id));
        log.error("findBookAuthorById() - end: author = {}", author);
        return author;
    }
}
