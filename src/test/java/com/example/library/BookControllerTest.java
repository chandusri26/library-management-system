package com.example.library;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookService bookService;

    @Test
    void getAllBooks_returnsJson() throws Exception {
        Book b = new Book("T","A","I");
        b.setId(1L);
        when(bookService.getAllBooks()).thenReturn(List.of(b));

        mockMvc.perform(get("/books"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].title").value("T"));
    }

    @Test
    void createBook_returnsSaved() throws Exception {
        Book b = new Book("T","A","I");
        Book saved = new Book("T","A","I"); saved.setId(1L);
        when(bookService.saveBook(any(Book.class))).thenReturn(saved);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(b)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deleteBook_returnsMessage() throws Exception {
        doNothing().when(bookService).deleteBook(1L);
        mockMvc.perform(delete("/books/1")).andExpect(status().isOk())
               .andExpect(content().string(org.hamcrest.Matchers.containsString("Book deleted")));
    }
}
