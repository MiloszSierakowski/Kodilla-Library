package com.example.library.service;

import com.example.library.domain.Rental;
import com.example.library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;

    public Rental saveNewRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Optional<Rental> findById(Long id){
        return rentalRepository.findById(id);
    }
}
