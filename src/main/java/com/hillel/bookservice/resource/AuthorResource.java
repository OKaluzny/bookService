package com.hillel.bookservice.resource;

import com.hillel.bookservice.domain.Author;
import com.hillel.bookservice.domain.Book;
import com.hillel.bookservice.repository.AuthorRepository;
import com.hillel.bookservice.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthorResource {

    private final AuthorRepository authorRepository;
    //private final PublisherRepository publisherRepository;
    private final AuthorService authorService;

    @PostMapping("/authors")
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.saveBookAuthor(author);
    }

    @GetMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getBookAuthorById(id);
    }

    @PutMapping("/authors/{id}")
    @ResponseStatus(HttpStatus.OK)
    //@Transactional
    public Author addBook(@PathVariable("id") Long id, @RequestBody Book book) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id = " + id));

        author.getBooks().add(book);
        return authorRepository.save(author);
    }

    @GetMapping("/authors/publishers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Author getAuthorByPublisherId(@PathVariable("id") Long id) {

        return authorRepository.findAuthorByPublisherPublisherId(id);

    }

}
