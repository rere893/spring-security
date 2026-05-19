package com.example.repository;

import com.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // 標準メソッドはJpaRepositoryから自動的に提供される
    // findAll(), findById(), save(), delete(), deleteById() など
    
    // カスタムメソッド
    Optional<Book> findByIsbn(String isbn);
    
    List<Book> findByAuthor(String author);
    
    List<Book> findByTitleContaining(String title);
    
    // JPQL例
    @Query("SELECT b FROM Book b WHERE b.author = :author ORDER BY b.title")
    List<Book> findBooksByAuthorOrdered(@Param("author") String author);
}