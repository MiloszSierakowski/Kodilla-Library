package com.example.library.service;

import com.example.library.controller.CopyOfBookNotFoundException;
import com.example.library.domain.CopyOfBook;
import com.example.library.repository.CopyOfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CopyOfBookService {
    private final CopyOfBookRepository copyOfBookRepository;

    public CopyOfBook saveCopyOfBook(CopyOfBook copyOfBook) {
        return copyOfBookRepository.save(copyOfBook);
    }

    public CopyOfBook findById(Long id) throws CopyOfBookNotFoundException {
        return copyOfBookRepository.findById(id).orElseThrow(CopyOfBookNotFoundException::new);
    }

    public List<CopyOfBook> findAvailableCopyOfBook(String title) {
        return copyOfBookRepository.findAvailableCopyOfBook(title);
    }
}
