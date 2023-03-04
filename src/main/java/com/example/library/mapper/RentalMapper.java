package com.example.library.mapper;

import com.example.library.domain.Rental;
import com.example.library.domain.RentalDto;

public class RentalMapper {
    public Rental mapToRental(RentalDto rentalDto) {
        return new Rental(rentalDto.getId(), rentalDto.getCopyOfBookId(), rentalDto.getReaderId(),
                rentalDto.getRentDate(), rentalDto.getReturnDate());
    }

    public RentalDto mapToRentalDto(Rental rental) {
        return new RentalDto(rental.getId(), rental.getCopyOfBookId(), rental.getReaderId(),
                rental.getRentDate(), rental.getReturnDate());
    }
}
