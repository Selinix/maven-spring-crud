package com.selinix.service;

import com.selinix.model.Book;

import java.util.List;

public interface BookService {

    public List<Book> listAllBooks();

    public void addBook(Book book);

    public void updateBook (Book book);

    public void deleteBook (int id);

    public Book findBookById (int id);
}
