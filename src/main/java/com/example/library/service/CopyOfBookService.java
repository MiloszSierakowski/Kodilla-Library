package com.example.library.service;

import com.example.library.domain.CopyOfBook;
import com.example.library.repository.CopyOfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CopyOfBookService {
    private final CopyOfBookRepository copyOfBookRepository;

    public CopyOfBook saveCopyOfBook(CopyOfBook copyOfBook){
        return copyOfBookRepository.save(copyOfBook);
    }

    public Optional<CopyOfBook> findById(Long id){
        return copyOfBookRepository.findById(id);
    }

    public List<CopyOfBook> findAvailableCopyOfBook(String title){
        return copyOfBookRepository.findAvailableCopyOfBook(title);
    }
}
