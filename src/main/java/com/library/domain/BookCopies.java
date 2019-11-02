package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK_COPIES")
public class BookCopies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_COPY_ID", unique = true)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private BookTitles title;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "HIRE_ID")
    private HiredBooks hiredBooks;

    @Column(name = "BOOK_STATUS")
    private String bookStatus;
}
