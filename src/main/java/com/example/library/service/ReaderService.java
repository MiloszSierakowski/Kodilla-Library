package com.example.library.service;

import com.example.library.controller.ReaderNotFoundException;
import com.example.library.domain.Reader;
import com.example.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;

    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader findById(Long id) throws ReaderNotFoundException {
        return readerRepository.findById(id).orElseThrow(ReaderNotFoundException::new);
    }
}
