package com.tpe.dto;

import com.tpe.domain.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookDTO {

private Long id;

@NotBlank(message = "Title can not be blank")
private String title;

@NotBlank(message = "Author name can not be blank")
@Size(min = 2,max = 70,message = "Author name ${validatedValue} can be between {min} and {max} chars! ")
private String author;

private String publicationDate;

//private String ownerName;


    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getName();
        this.author = book.getAuthor();
        this.publicationDate = book.getPublicationDate().toString();
    }
}
