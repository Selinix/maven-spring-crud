package com.selinix.dao;

import com.selinix.model.Book;

import java.util.List;

public interface BookDao {

    public List<Book> listAllBooks();

    public void addBook(Book book);

    public void updateBook (Book book);

    public void deleteBook (int id);

    public Book findBookById (int id);
}
