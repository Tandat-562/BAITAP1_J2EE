package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public String addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return "Added book successfully!";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        boolean ok = bookService.updateBook(id, updatedBook);
        return ok ? "Updated book successfully!" : "Book not found!";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        boolean ok = bookService.deleteBook(id);
        return ok ? "Deleted book successfully!" : "Book not found!";
    }
}
