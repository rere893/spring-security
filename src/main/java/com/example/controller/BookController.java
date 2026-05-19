package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // ページA: 一覧表示
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("newBook", new Book());
        return "books";
    }

    // 書籍登録（POST）
    @PostMapping("/create")
    public String createBook(@ModelAttribute Book book) {
        bookService.createBook(book);
        return "redirect:/books";
    }

    // ページB: 編集画面表示
    @GetMapping("/edit")
    public String editBookForm(@RequestParam Long id, Model model) {
        Book book = bookService.getBookById(id).orElse(null);
        if (book != null) {
            model.addAttribute("book", book);
            return "edit";
        }
        return "redirect:/books";
    }

    // 書籍更新（POST）
    @PostMapping("/edit")
    public String updateBook(@RequestParam Long id, @ModelAttribute Book bookDetails) {
        bookService.updateBook(id, bookDetails);
        return "redirect:/books";
    }

    // 書籍削除（POST）
    @PostMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}