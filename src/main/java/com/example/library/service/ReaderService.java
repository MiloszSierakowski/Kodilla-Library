package com.example.library.service;

import com.example.library.domain.Reader;
import com.example.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    public Reader saveReader(Reader reader){
        return readerRepository.save(reader);
    }
    public Optional<Reader> findById(Long id){
        return readerRepository.findById(id);
    }
}
