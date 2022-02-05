package com.hillel.bookservice;

import com.hillel.bookservice.domain.Author;
import com.hillel.bookservice.repository.AuthorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RepositoryTests {

    @Autowired
    private AuthorRepository repository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveAuthorTest(){

        Author author = Author.builder()
                .firstName("Mark")
                .lastName("Lafore")
                .build();

        repository.save(author);

        Assertions.assertThat(author.getAuthorId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getAuthorTest(){

        Author author = repository.findById(1L).orElseThrow();

        Assertions.assertThat(author.getAuthorId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    public void getListOfAuthorTest(){

        List<Author> authorList = repository.findAll();

        Assertions.assertThat(authorList.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateAuthorTest(){

        Author author = repository.findById(1L).get();

        author.setFirstName("Martin");

        Author authorUpdated =  repository.save(author);

        Assertions.assertThat(authorUpdated.getFirstName()).isEqualTo("Martin");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteAuthorTest(){

        Author author = repository.findById(1L).get();

        repository.delete(author);

        //repository.deleteById(1L);

        Author author1 = null;

        Optional<Author> optionalAuthor = Optional.ofNullable(repository.getAuthorByFirstName("Martin"));

        if(optionalAuthor.isPresent()){
            author1 = optionalAuthor.get();
        }

        Assertions.assertThat(author1).isNull();
    }

}
