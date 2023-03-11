package com.example.library.mapper;

import com.example.library.domain.Rental;
import com.example.library.domain.RentalDto;
import org.springframework.stereotype.Service;

@Service
public class RentalMapper {
    public Rental mapToRental(RentalDto rentalDto) {
        return new Rental(rentalDto.getId(), rentalDto.getCopyOfBook(), rentalDto.getReader(),
                rentalDto.getRentDate(), rentalDto.getReturnDate());
    }

    public RentalDto mapToRentalDto(Rental rental) {
        return new RentalDto(rental.getId(), rental.getCopyOfBook(), rental.getReader(),
                rental.getRentDate(), rental.getReturnDate());
    }
}
