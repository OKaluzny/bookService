package com.hillel.bookservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.bookservice.domain.Author;
import com.hillel.bookservice.repository.AuthorRepository;
import com.hillel.bookservice.resource.AuthorResource;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorResource.class)
public class ControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    AuthorRepository repository;

    @Ignore
    @Test
    public void createAuthor_success() throws Exception {
        Author author = Author.builder()
                .firstName("John")
                .build();

        Mockito.when(repository.save(author)).thenReturn(author);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(author));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", is("John")));
    }

    @Ignore
    @Test
    public void getAuthorById_success() throws Exception {

        Author author = Author.builder()
                .firstName("Mark")
                .lastName("Lafore")
                .build();

        Mockito.when(repository.findById(author.getAuthorId())).thenReturn(java.util.Optional.of(author));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/authors/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", is("Mark")));
    }

    @Ignore
    @Test
    public void getAllAuthors_success() throws Exception {

        Author author = Author.builder()
                .firstName("Mark")
                .lastName("Lafore")
                .build();

        List<Author> records = new ArrayList<>(Arrays.asList(author));

        repository.save(author);

        Mockito.when(repository.findAll()).thenReturn(records);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/authors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[2].firstName", is("Mark")));
    }

}
