package com.example.library;

import java.util.List;

public interface BookService {
    // Existing methods
    Book saveBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);

    // New methods for issue and return
    void issueBook(Long id);
    void returnBook(Long id);
}
