package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

@NotBlank(message = "Book name can not be space!")
@Column(nullable = false)
private String name;

@NotBlank(message = "Author name can not be space!")
@Column(nullable = false)
private String author;

@NotNull
 @PastOrPresent(message = "Publication date can not be future date!")
@JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
 private LocalDate publicationDate;

}
