package com.example.library.repository;

import com.example.library.domain.CopyOfBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CopyOfBookRepository extends CrudRepository<CopyOfBook, Long> {
/*    @Query
    List<CopyOfBook> findAvailableCopyOfBook(@Param("title") String title);*/
}
