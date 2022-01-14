package com.hillel.bookservice.resource;

import com.hillel.bookservice.domain.Author;
import com.hillel.bookservice.domain.Publisher;
import com.hillel.bookservice.repository.AuthorRepository;
import com.hillel.bookservice.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PublisherResource {

    private final PublisherRepository repository;
    private final AuthorRepository authorRepository;

    @PostMapping("/publishers")
    @ResponseStatus(HttpStatus.CREATED)
    public Publisher savePublisher(@RequestBody Publisher publisher) {
        return repository.save(publisher);
    }

    @GetMapping("/publishers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher getPublisherById(@PathVariable Long id) {
        Publisher publisher = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found with id = " + id));
        return publisher;
    }

    //@PutMapping("/publishers/{id}/authors")
    //@PostMapping("/publishers/{id}/authors")
    @PatchMapping("/publishers/{id}/authors")
    @ResponseStatus(HttpStatus.OK)
    public Publisher addAuthor(@PathVariable("id") Long id, @RequestParam Long authorId) {

        Publisher publisher = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found with id = " + id));

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Publisher not found with id = " + authorId));

        publisher.getAuthors().add(author);
        return repository.save(publisher);
    }

    @GetMapping("/publishersBy/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publisher getAuthorByPublisher(@PathVariable("id") Long id) {

        return repository.findPublisher(id);

    }

    @GetMapping("/publishersByName")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Publisher> getPublisherName(@RequestParam(value = "name") String name) {

        return repository.findByName(name);

    }
}
