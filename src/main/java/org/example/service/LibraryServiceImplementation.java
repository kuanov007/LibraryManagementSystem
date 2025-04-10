package org.example.service;

import org.example.entities.Book;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

import static org.example.db.DB.books;

public class LibraryServiceImplementation implements LibraryService {
    @Override
    public List<Book> filterBooks(Predicate<Book> predicate) {
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (predicate.test(book)) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    @Override
    public List<String> getBookTitles(Function<Book, String> function) {
        List<String> bookTitles = new ArrayList<>();
        for (Book book : books) {
            bookTitles.add(function.apply(book));
        }
        return bookTitles;
    }

    @Override
    public void printBooks(Consumer<Book> consumer) {
        for (Book book : books) {
            consumer.accept(book);
        }
    }

    @Override
    public void addBookFromSupplier(Supplier<Book> supplier) {
        books.add(supplier.get());
    }

    @Override
    public void updateBookTitle(Book book, UnaryOperator<String> unaryOperator) {
        book.setTitle(unaryOperator.apply(book.getTitle()));
    }

    @Override
    public Book findOldestBook(BinaryOperator<Book> binaryOperator) {
        Book oldestBook = null;
        for (Book book : books) {
            oldestBook = binaryOperator.apply(oldestBook, book);
        }
        return oldestBook;
    }

    @Override
    public void getSortedBooks(Comparator<Book> comparator) {
        for (int i = 0; i < books.size(); i++) {
            for (int j = i + 1; j < books.size(); j++) {
                comparator.compare(books.get(i), books.get(j));
            }
        }
    }
}
