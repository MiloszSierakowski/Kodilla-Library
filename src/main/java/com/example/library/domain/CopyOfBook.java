package com.example.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "COPY_OF_BOOK")
public class CopyOfBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book bookId;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "IS_RENTAL")
    private boolean isRental;
    @OneToMany(
            targetEntity = Rental.class,
            mappedBy = "COPY_OF_BOOK",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Rental> rentalList = new ArrayList<>();
}
