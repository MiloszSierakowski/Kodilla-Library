package com.example.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "COPY_OF_BOOK.findAvailableCopyOfBook",
        query = "FROM COPY_OF_BOOK C JOIN COPY_OF_BOOK.book B " +
                "where C.isRental = false AND B.title = :title"
)
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
    private Book book;
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
