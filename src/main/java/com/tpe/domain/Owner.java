package com.tpe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_owner")
@ToString
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)

    private Long id;

    @NotBlank(message = "Please enter valid name")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Please enter valid surname")
    @Column(nullable = false)
    private String lastName;

    private String phoneNumber;

    @Email(message = "Please enter valid email")
@Column(nullable = false,unique = true)
private String email;

    @Setter(AccessLevel.NONE)
    private LocalDateTime registirationDate=LocalDateTime.now();

@OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
private List<Book> books = new ArrayList<>();

public void adBook(Book book){
    books.add(book);
    book.setOwner(this);
}

}
