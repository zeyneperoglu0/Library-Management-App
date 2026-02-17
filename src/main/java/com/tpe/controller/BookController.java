package com.tpe.controller;

import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import com.tpe.response.Response;
import com.tpe.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Response<BookDTO>> save(@Valid @RequestBody BookDTO bookDTO) {
        bookService.saveBook(bookDTO);

        Response<BookDTO> response = new Response<>(
                true,
                "Book is saved successfully",
                bookDTO
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<BookDTO>>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();

        Response<List<BookDTO>> response = new Response<>(
                true,
                "All books are listed",
                books

        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("{id}")
    public ResponseEntity<Response<BookDTO>> findBookById(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        BookDTO bookDTO = new BookDTO(book);
        Response<BookDTO> response = new Response<>(
                true,
                "Book is found By Id " + id,
                bookDTO


        );
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<String>> deleteBookById(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        Response<String> response = new Response<>(
                true,
                "Book is deleted successfully"
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<String>> updateBookById(@PathVariable("id") Long id, @Valid @RequestBody BookDTO bookDTO) {
        bookService.updateBookById(id, bookDTO);
        Response<String> response = new Response<>(
                true,
                "Book is updated successfully"
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/page")
    public ResponseEntity<Response<Page<BookDTO>>> getBooksByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "title") String prop,
            @RequestParam(value = "direction", defaultValue = "ASC") Sort.Direction direction

    ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<BookDTO> bookPages = bookService.getAllBooksWithPage(pageable);
        Response<Page<BookDTO>> response = new Response<>(
                true,
                "Books are getting with page",
                bookPages
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/author")
    public ResponseEntity<Response<List<BookDTO>>> getBooksByAuthor(@RequestParam("author") String author) {
        List<BookDTO> books = bookService.getAllBooksWithAuthor(author);

        Response<List<BookDTO>> response = new Response<>(
                true,
                "Books listed by author name",
                books
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Response<List<BookDTO>>> findByTitleAndDate(
            @RequestParam("title") String title,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){

        List<BookDTO> bookDTOS = bookService.findByTitleAndPublicationDate(title,date);
        Response<List<BookDTO>>response  = new Response<>(
                true,
                "Books are found by title and date",
                bookDTOS
        );
        return ResponseEntity.ok(response);
    }


}
