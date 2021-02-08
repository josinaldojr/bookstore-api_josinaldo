package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.book.BookRepository;
import br.com.bookstore.bookstore.exceptions.BookAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SaveBookServiceImpl implements SaveBookService {

    private final BookRepository bookRepository;

    @Override
    public void insert(Book book) {
        Optional<Book> isbnInDB = bookRepository.findByIsbn(book.getIsbn());
        if(isbnInDB.isPresent()) {
            throw new BookAlreadyExistException();
        }

        bookRepository.save(book);
    }
}
