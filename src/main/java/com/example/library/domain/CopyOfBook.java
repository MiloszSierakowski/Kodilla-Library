package com.example.library.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/*@NamedNativeQuery(
        name = "COPY_OF_BOOK.findAvailableCopyOfBook",
        query = "SELECT * FROM kodilla_library.copy_of_book C JOIN kodilla_library.book B ON C.book_id = B.id " +
                "where C.is_rental = false AND B.title = :title"
)*/
@NamedQuery(
        name = "COPY_OF_BOOK.findAvailableCopyOfBook",
        query = "FROM COPY_OF_BOOK C JOIN BOOK B ON C.book.id = B.id " +
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
            mappedBy = "copyOfBook",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Rental> rentalList = new ArrayList<>();
}
