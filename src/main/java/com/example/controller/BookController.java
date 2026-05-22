package com.example.controller;

import com.example.form.BookForm;
import com.example.model.Book;
import com.example.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    // 一覧表示
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("bookForm", new BookForm());
        return "books";
    }

    // 書籍登録（バリデーション付き）
    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute BookForm bookForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // バリデーションエラーがある場合、一覧画面に戻す
            model.addAttribute("books", bookService.getAllBooks());
            return "books";
        }

        // バリデーション成功時、Bookオブジェクトに変換して保存
        Book book = new Book();
        book.setTitle(bookForm.getTitle());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());
        book.setPrice(bookForm.getPrice());
        bookService.createBook(book);

        return "redirect:/books";
    }

    // 編集画面表示
    @GetMapping("/edit")
    public String editBookForm(@RequestParam Long id, Model model) {
        Book book = bookService.getBookById(id).orElse(null);
        if (book != null) {
            BookForm bookForm = new BookForm();
            bookForm.setTitle(book.getTitle());
            bookForm.setAuthor(book.getAuthor());
            bookForm.setIsbn(book.getIsbn());
            bookForm.setPrice(book.getPrice());
            model.addAttribute("bookForm", bookForm);
            model.addAttribute("bookId", id);
            return "edit";
        }
        return "redirect:/books";
    }

    // 書籍更新（バリデーション付き）
    @PostMapping("/edit")
    public String updateBook(@RequestParam Long id, @Valid @ModelAttribute BookForm bookForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // バリデーションエラーがある場合、編集画面に戻す
            model.addAttribute("bookId", id);
            return "edit";
        }

        // バリデーション成功時、書籍を更新
        Book bookDetails = new Book();
        bookDetails.setTitle(bookForm.getTitle());
        bookDetails.setAuthor(bookForm.getAuthor());
        bookDetails.setIsbn(bookForm.getIsbn());
        bookDetails.setPrice(bookForm.getPrice());
        bookService.updateBook(id, bookDetails);

        return "redirect:/books";
    }

    // 削除
    @PostMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}