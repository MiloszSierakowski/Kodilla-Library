package com.example.library.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "COPY_OF_BOOK")
public class CopyOfBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "IS_RENTAL", nullable = false)
    private boolean isRental;
    @OneToMany(
            targetEntity = Rental.class,
            mappedBy = "copyOfBook",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<Rental> rentalList = new ArrayList<>();
}
