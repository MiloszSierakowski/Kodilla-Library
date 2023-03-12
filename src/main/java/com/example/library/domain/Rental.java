package com.example.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RENTAL")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "COPY_OF_BOOK_ID", nullable = false)
    private CopyOfBook copyOfBook;
    @ManyToOne()
    @JoinColumn(name = "USER_ID", nullable = false)
    private Reader reader;
    @Column(name = "RENT_DATE", nullable = false)
    private LocalDate rentDate;
    @Column(name = "RETURN_DATE")
    private LocalDate returnDate;
}
