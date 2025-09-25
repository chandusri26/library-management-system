package com.example.library;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test") // Use H2 in-memory DB
class BookRepositoryTest {

    @Autowired
    BookRepository repo;

    @Test
    void saveAndFind() {
        Book b = new Book("Title","Author","ISBN");
        Book saved = repo.save(b);
        Optional<Book> found = repo.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Title", found.get().getTitle());
    }
}
