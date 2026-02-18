package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.domain.Owner;
import com.tpe.dto.BookDTO;
import com.tpe.exception.BookNotFoundException;
import com.tpe.exception.ConflictException;
import com.tpe.repository.BookRepository;
import com.tpe.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final OwnerService ownerService;

    public void saveBook(BookDTO bookDTO) {

        Book book = new Book();
        book.setName(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationDate(LocalDate.parse(bookDTO.getPublicationDate()));

        bookRepository.save(book);
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream().map(BookDTO::new).collect(Collectors.toList());
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book not found ID : " + id));


    }

    public void deleteBookById(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    public void updateBookById(Long id, BookDTO bookDTO) {
        Book book = getBookById(id);

        book.setName(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublicationDate(LocalDate.parse(bookDTO.getPublicationDate()));
        bookRepository.save(book);


    }

    public Page<BookDTO> getAllBooksWithPage(Pageable pageable) {
        Page<Book> bookPage = bookRepository.findAll(pageable);

        return bookPage.map(BookDTO::new);

    }

    public List<BookDTO> getAllBooksWithAuthor(String author) {
        List<Book> books = bookRepository.findByAuthorWithJPQL(author);

        if (books.isEmpty()) {
            throw new BookNotFoundException("Book not found by author named " + author);
        }
        return books.stream().map(BookDTO::new).collect(Collectors.toList());

    }

    public List<BookDTO> findByTitleAndPublicationDate(String title, LocalDate date) {
        List<Book> books = bookRepository.findByTitleAndPublicationDate(title, date);
        if (books.isEmpty()) {
            throw new BookNotFoundException("Book not found by title and date " + title + " " + date);
        }
        return books.stream().map(BookDTO::new).collect(Collectors.toList());
    }


    public void addBookToOwner(Long bookId, Long ownerId) {

        Book book = getBookById(bookId);

        Owner owner = ownerService.findOwnerById(ownerId);

        if (book.getOwner()!=null) {
        throw new ConflictException("This book already taken! ");
        }

        book.setOwner(owner);

        bookRepository.save(book);
    }


}
