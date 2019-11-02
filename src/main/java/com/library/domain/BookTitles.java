package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK_TITLES")
public class BookTitles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TITLE_ID", unique = true)
    private Long id;

    @NotNull
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @Column(name = "AUTHOR")
    private String author;

    @NotNull
    @Column(name = "YEAR_OF_PUBLICATION")
    private String yearOfPublication;

    @Transient
    @OneToMany(
            targetEntity = BookCopies.class,
            mappedBy = "title",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    private List<BookCopies> bookCopies = new ArrayList<>();

    @Column(name = "NUMBER_OF_COPIES")
    private int numberOfCopies;

    @Column(name = "BOOKS_READY_TO_RENT")
    private int booksReadyToRent;
}
