package com.hillel.bookservice;

import com.hillel.bookservice.domain.Author;
import com.hillel.bookservice.repository.AuthorRepository;
import com.hillel.bookservice.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

    @Mock
    private AuthorRepository repository;

    @InjectMocks
    private AuthorService service;

    @Test
    public void whenSaveAuthor_shouldReturnAuthor() {
        Author author = new Author();
        author.setFirstName("Mark");

        when(repository.save(ArgumentMatchers.any(Author.class))).thenReturn(author);

        Author created = service.saveBookAuthor(author);

        assertThat(created.getFirstName()).isSameAs(author.getFirstName());
        verify(repository).save(author);
    }

    @Test
    public void whenGivenId_shouldReturnAuthor_ifFound() {
        Author author = new Author();
        author.setAuthorId(88L);

        when(repository.findById(author.getAuthorId())).thenReturn(Optional.of(author));

        Author expected = service.getBookAuthorById(author.getAuthorId());

        assertThat(expected).isSameAs(author);
        verify(repository).findById(author.getAuthorId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void should_throw_exception_when_author_doesnt_exist() {
        Author author = new Author();
        author.setAuthorId(89L);
        author.setFirstName("Mark");

        given(repository.findById(anyLong())).willReturn(Optional.empty());
        service.getBookAuthorById(author.getAuthorId());
    }
}
