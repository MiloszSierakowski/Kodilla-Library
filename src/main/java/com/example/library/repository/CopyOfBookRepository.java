package com.example.library.repository;

import com.example.library.domain.CopyOfBook;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CopyOfBookRepository extends CrudRepository<CopyOfBook, Long> {
    @Query(
            "SELECT C FROM CopyOfBook C JOIN Book B ON C.book.id = B.id " +
                    "where C.rented = false AND B.title = :title"
    )
    List<CopyOfBook> findAvailableCopyOfBook(@Param("title") String title);

}
