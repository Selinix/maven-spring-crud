package com.selinix.controller;

import com.selinix.model.Book;
import com.selinix.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/")
    public ModelAndView homepage () {
        return new ModelAndView("redirect:/list");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list () {

        ModelAndView model = new ModelAndView("book_page");

        List<Book> list = bookService.listAllBooks();
        model.addObject("listBook", list);

        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView add () {

        ModelAndView model = new ModelAndView("book_form");

        Book book = new Book();
        model.addObject("bookForm", book);

        return model;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView update (@PathVariable("id") int id) {

        ModelAndView model = new ModelAndView("book_form");

        Book book = bookService.findBookById(id);
        model.addObject("bookForm", book);

        return model;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save (@ModelAttribute("bookForm") Book book) {
        if (book != null && book.getId() != null) {
            //update
            bookService.updateBook(book);
        } else {
            //add new
            bookService.addBook(book);
        }

        return new ModelAndView("redirect:/list");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete (@PathVariable("id") int id) {
        bookService.deleteBook(id);

        return new ModelAndView("redirect:/list");
    }
}
