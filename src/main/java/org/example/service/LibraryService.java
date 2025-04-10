package org.example.service;

import org.example.entities.Book;

import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public interface LibraryService {
    List<Book> filterBooks(Predicate<Book> predicate);

    List<String> getBookTitles(Function<Book, String> function);

    void printBooks(Consumer<Book> consumer);

    void addBookFromSupplier(Supplier<Book> supplier);

    void updateBookTitle(Book book, UnaryOperator<String> unaryOperator);

    Book findOldestBook(BinaryOperator<Book> binaryOperator);

    void getSortedBooks(Comparator<Book> comparator);
}
