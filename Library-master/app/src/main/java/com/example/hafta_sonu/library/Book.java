package com.example.hafta_sonu.library;


public class Book {
    Writer writer;
    String bookName;

    public Book(Writer writer, String bookName) {
        this.writer = writer;
        this.bookName = bookName;
    }

    public Book(String bookName) {
        this.bookName = bookName;
        this.writer = new Writer("Anonim");
    }
}
