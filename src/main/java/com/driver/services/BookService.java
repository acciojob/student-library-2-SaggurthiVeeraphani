package com.driver.services;

import com.driver.models.Author;
import com.driver.models.Book;
import com.driver.repositories.AuthorRepository;
import com.driver.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    @Autowired
    BookRepository bookRepository2;

    @Autowired
    AuthorRepository authorRepository;

    public void createBook(Book book){

        bookRepository2.save(book);
    }

    public List<Book> getBooks(String genre, boolean available, String author){
        List<Book> books = new ArrayList<>(); //find the elements of the list by yourself
        if(author!=null){
            int id1 = 0;
            List<Author> authorList = authorRepository.findAll();
            for(Author author1 : authorList){
                if(author1.getName()==author){
                    id1 = author1.getId();
                    break;
                }
            }
        Author author2 = authorRepository.findById(id1).get();
         List<Book> bookList = author2.getBooksWritten();
         for(Book book:bookList){
             if(available){
                 if(book.isAvailable() == true && (book.getGenre().equals(genre))){
                     books.add(book);
                   }
             }
             else{
                 if(book.isAvailable()==false && (book.getGenre().equals(genre))){
                     books.add(book);
                 }
             }
         }
        }
        else {
            List<Book> bookList = bookRepository2.findAll();
            for (Book book : bookList) {
                if (available) {
                    if (book.isAvailable() && (book.getGenre().equals(genre))) {
                        books.add(book);
                    }
                } else {
                    if (!book.isAvailable() && (book.getGenre().equals(genre))) {
                        books.add(book);
                    }
                }
            }
        }

        return books;
    }
}
