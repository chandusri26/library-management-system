package com.example.library;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    @Test
    void saveBook_shouldReturnSavedBook() {
        Book b = new Book("T","A","I");
        when(bookRepository.save(any(Book.class))).thenAnswer(i -> {
            Book arg = i.getArgument(0);
            arg.setId(1L);
            return arg;
        });

        Book saved = bookService.saveBook(b);
        assertNotNull(saved.getId());
        assertEquals("T", saved.getTitle());
        verify(bookRepository).save(b);
    }

    @Test
    void getAllBooks_returnsList() {
        List<Book> list = List.of(new Book("T","A","I"));
        when(bookRepository.findAll()).thenReturn(list);

        List<Book> res = bookService.getAllBooks();
        assertEquals(1, res.size());
        assertEquals("T", res.get(0).getTitle());
    }

    @Test
    void getBookById_returnsBook() {
        Book book = new Book("T","A","I");
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book res = bookService.getBookById(1L);
        assertNotNull(res);
        assertEquals("T", res.getTitle());
    }

    @Test
    void deleteBook_callsRepo() {
        doNothing().when(bookRepository).deleteById(1L);
        bookService.deleteBook(1L);
        verify(bookRepository).deleteById(1L);
    }
}
