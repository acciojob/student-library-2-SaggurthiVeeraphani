package com.driver.services;

import com.driver.models.Author;
import com.driver.models.Genre;
import com.driver.repositories.AuthorRepository;
import com.driver.repositories.BookRepository;
import com.driver.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository2;

    @Autowired
    AuthorRepository authorRepository1;

    public void createBook(Book book){




//        int authorId = book.getAuthor().getId();
//
//        Author author =  authorRepository1.findById(authorId).get();
//        List<Book> booksWritten = author.getBooksWritten();
//        if(booksWritten==null){
//            booksWritten = new ArrayList<>();
//            booksWritten.add(book);
//        }else{
//            booksWritten.add(book);
//        }
//        author.setBooksWritten(booksWritten);
//        book.setAuthor(author);
////        bookRepository2.save(book);
//
//        authorRepository1.save(author);

        try {
            int id = book.getAuthor().getId();
            Author author = authorRepository1.findById(id).get();
            List<Book> bookList = author.getBooksWritten();
            if(bookList==null) {
                bookList = new ArrayList<>();
            }
            bookList.add(book);
            book.setAuthor(author);
            author.setBooksWritten(bookList);
            authorRepository1.save(author);

        }
        catch (Exception e) {

            bookRepository2.save(book);
        }



    }

    //This has to be rectified....and given a thought

    public List<Book> getBooks(String genre, boolean available, String author){


        if(genre != null && author != null){
            return bookRepository2.findBooksByGenreAuthor(genre, author, available);
        }else if(genre != null){
            return bookRepository2.findBooksByGenre(genre, available);
        }else if(author != null){
            return bookRepository2.findBooksByAuthor(author, available);
        }else{
            return bookRepository2.findByAvailability(available);
        }
    }
}