package com.example.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RENTAL")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "COPY_OF_BOOK_ID")
    private CopyOfBook copyOfBook;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Reader reader;
    @Column(name = "RENT_DATE")
    private Date rentDate;
    @Column(name = "RETURN_DATE")
    private Date returnDate;
}
