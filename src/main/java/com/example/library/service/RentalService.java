package com.example.library.service;

import com.example.library.controller.RentalNotFoundException;
import com.example.library.domain.Rental;
import com.example.library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;

    public Rental saveNewRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental findById(Long id) throws RentalNotFoundException {
        return rentalRepository.findById(id).orElseThrow(RentalNotFoundException::new);
    }
}
