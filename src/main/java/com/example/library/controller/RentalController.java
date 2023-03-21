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

        CopyOfBook copyOfBook = copyOfBookService.findById(rentalDto.getCopyOfBookId());
        Reader reader = readerService.findById(rentalDto.getReaderId());

        rental.setCopyOfBook(copyOfBook);
        rental.setReader(reader);
        rentalService.saveNewRental(rental);

        copyOfBook.setRented(true);
        copyOfBookService.saveCopyOfBook(copyOfBook);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalDto> finishRental(@RequestBody RentalDto rentalDto) throws RentalNotFoundException {
        Rental rental = rentalService.findById(rentalDto.getId());

        CopyOfBook copyOfBook = rental.getCopyOfBook();
        rental.setReturnDate(rentalDto.getReturnDate());

        rentalService.saveNewRental(rental);
        copyOfBook.setRented(false);
        copyOfBookService.saveCopyOfBook(copyOfBook);
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(rental));
    }
}
