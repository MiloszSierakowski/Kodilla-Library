package com.example.library.service;

import com.example.library.repository.CopyOfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CopyOfBookService {
    private final CopyOfBookRepository copyOfBookRepository;
}
