package com.tpe.repository;

import com.tpe.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {


@Query("SELECT b FROM Book b WHERE b.author=:author")
 public List<Book> findByAuthorWithJPQL(@Param("author") String author);


 @Query("SELECT b FROM Book b WHERE b.title=:title AND  b.publicationDate=:publicationDate")
 public List<Book> findByTitleAndPublicationDate(String title , LocalDate publicationDate);












}
