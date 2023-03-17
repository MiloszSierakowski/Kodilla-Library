package com.example.library.controller;

import com.example.library.domain.CopyOfBook;
import com.example.library.domain.Reader;
import com.example.library.domain.Rental;
import com.example.library.domain.RentalDto;
import com.example.library.mapper.RentalMapper;
import com.example.library.service.CopyOfBookService;
import com.example.library.service.ReaderService;
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
    private final ReaderService readerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewRental(@RequestBody RentalDto rentalDto) throws CopyOfBookNotFoundException, ReaderNotFoundException {
        Rental rental = rentalMapper.mapToRental(rentalDto);

        Optional<CopyOfBook> copyOfBook = copyOfBookService.findById(rentalDto.getCopyOfBookId());
        Optional<Reader> reader = readerService.findById(rentalDto.getReaderId());

        if (copyOfBook.isPresent() && reader.isPresent()) {
            rental.setCopyOfBook(copyOfBook.get());
            rental.setReader(reader.get());
            rentalService.saveNewRental(rental);

            copyOfBook.get().setRented(true);
            copyOfBookService.saveCopyOfBook(copyOfBook.get());
        } else if (copyOfBook.isEmpty()) {
            throw new CopyOfBookNotFoundException();
        } else {
            throw new ReaderNotFoundException();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalDto> finishRental(@RequestBody RentalDto rentalDto) throws RentalNotFoundException {
        Optional<Rental> rental = rentalService.findById(rentalDto.getId());

        if (rental.isPresent()) {
            CopyOfBook copyOfBook = rental.get().getCopyOfBook();
            rental.get().setReturnDate(rentalDto.getReturnDate());

            rentalService.saveNewRental(rental.get());
            copyOfBook.setRented(false);
            copyOfBookService.saveCopyOfBook(copyOfBook);
        } else {
            throw new RentalNotFoundException();
        }
        return ResponseEntity.ok(rental.map(rentalMapper::mapToRentalDto).orElse(null));
    }
}
