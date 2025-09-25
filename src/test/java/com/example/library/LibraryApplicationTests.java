package com.example.library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Use H2 in-memory DB
class LibraryApplicationTests {

    @Test
    void contextLoads() {
    }
}
