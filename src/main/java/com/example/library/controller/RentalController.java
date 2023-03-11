package com.example.library.controller;

import com.example.library.domain.CopyOfBook;
import com.example.library.domain.Rental;
import com.example.library.domain.RentalDto;
import com.example.library.mapper.RentalMapper;
import com.example.library.service.CopyOfBookService;
import com.example.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/rental")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RentalController {
    private final RentalMapper rentalMapper;
    private final RentalService rentalService;
    private final CopyOfBookService copyOfBookService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewRental(@RequestBody RentalDto rentalDto) throws CopyOfBookNotFoundException {
        Rental rental = rentalMapper.mapToRental(rentalDto);

        Optional<CopyOfBook> copyOfBook = copyOfBookService.findById(rental.getCopyOfBook().getId());

        if (copyOfBook.isPresent()) {
            rentalService.saveNewRental(rental);
            copyOfBook.get().setRental(true);
            copyOfBookService.saveCopyOfBook(copyOfBook.get());
        } else {
            throw new CopyOfBookNotFoundException();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> finishRental(@RequestBody RentalDto rentalDto) throws CopyOfBookNotFoundException, RentalNotFoundException {
        Optional<Rental> rental = rentalService.findById(rentalDto.getId());
        Optional<CopyOfBook> copyOfBook = copyOfBookService.findById(rentalDto.getCopyOfBook().getId());

        if (rental.isPresent() && copyOfBook.isPresent()) {
            rental.get().setReturnDate(rentalDto.getReturnDate());
            copyOfBook.get().setRental(false);
        } else {
            if (rental.isEmpty()){
                throw new RentalNotFoundException();
            }else {
                throw new CopyOfBookNotFoundException();
            }
        }

        return ResponseEntity.ok().build();
    }
}
