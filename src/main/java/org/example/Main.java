package org.example;

import org.example.entities.Author;
import org.example.entities.Book;
import org.example.entities.Category;
import org.example.service.LibraryServiceImplementation;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static LibraryServiceImplementation service = new LibraryServiceImplementation();

    public static void main(String[] args) {
        service.addBookFromSupplier(() -> {
            Book book = new Book();
            book.setTitle(readLine("Kitobni nomini kiriting: "));

            Author author = new Author();
            author.setName(readLine("Avtorni nomini kiriting: "));
            author.setYear(Byte.parseByte(readLine("Avtorni yilini kiriting: ")));
            book.setAuthor(author);

            Category category = new Category(readLine("Kategoriya nomini kiriting: "));
            book.setCategory(category);

            return book;
        });

        // Kitobni titlelarini olib beradi
        List<String> bookTitles = service.getBookTitles(Book::getTitle);

        //kitobni titlelarini uzunligi 5 dan kichik bo'lsa listga olmaydi!
        List<Book> books = service.filterBooks(book -> book.getTitle().length() < 5);

        //kitoblarni print qilish uchun ishlatiladi
        service.printBooks(book -> {
            System.out.printf("""
                    Kitob nomi: %s
                    Yili: %s
                    Avtori nomi: %s;
                    \n""", book.getTitle(), book.getAuthor().getYear(), book.getAuthor().getName());
        });

        //yili bo'yicha eng eski kitobni qaytaradi!
        service.findOldestBook((book, book2) -> {
            int year1 = book.getAuthor().getYear();
            int year2 = book2.getAuthor().getYear();
            return year1 < year2 ? book : book2;
        });
    }

    private static String readLine(String message) {
        System.out.print(message);
        String str = new Scanner(System.in).nextLine();
        if (str.isBlank()) {
            readLine(message);
        }
        return str;
    }
}