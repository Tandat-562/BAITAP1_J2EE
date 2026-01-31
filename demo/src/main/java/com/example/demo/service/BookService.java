package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean updateBook(int id, Book updatedBook) {
        Book existing = getBookById(id);
        if (existing == null) return false;

        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        return true;
    }

    public boolean deleteBook(int id) {
        Book existing = getBookById(id);
        if (existing == null) return false;

        books.remove(existing);
        return true;
    }
}
