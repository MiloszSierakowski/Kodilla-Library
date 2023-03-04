package com.example.library.service;

import com.example.library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
}
