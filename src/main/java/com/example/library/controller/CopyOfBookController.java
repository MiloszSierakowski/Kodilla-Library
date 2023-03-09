package com.example.library.controller;

import com.example.library.domain.CopyOfBook;
import com.example.library.domain.CopyOfBookDto;
import com.example.library.mapper.CopyOfBookMapper;
import com.example.library.service.CopyOfBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/copyOfBook")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CopyOfBookController {
//todo dodać logera i errory
    private final CopyOfBookService copyOfBookService;
    private final CopyOfBookMapper copyOfBookMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewCopyOfBook(@RequestBody CopyOfBookDto copyOfBookDto) {
        CopyOfBook copyOfBook = copyOfBookMapper.mapToCopyOfBook(copyOfBookDto);
        copyOfBookService.saveCopyOfBook(copyOfBook);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}/{status}")
    public ResponseEntity<CopyOfBookDto> changeCopyOfBookStatus(@PathVariable Long id, @PathVariable String status) {
        Optional<CopyOfBook> copyOfBook = copyOfBookService.findById(id);
        if (copyOfBook.isPresent()){
            copyOfBook.get().setStatus(status);
            copyOfBookService.saveCopyOfBook(copyOfBook.get());
        }else {
            //todo dodać logera
        }
        return ResponseEntity.ok(copyOfBook.map(copyOfBookMapper::mapToCopyOfBookDto).orElse(null));
    }

    @GetMapping(value = "/{title}")
    public ResponseEntity<List<CopyOfBookDto>> howManyCopiesAreAvailable(@PathVariable String title){
        List<CopyOfBook> copyOfBookList = copyOfBookService.findAvailableCopyOfBook(title);
        return ResponseEntity.ok(copyOfBookMapper.mapToListCopyOfBookDto(copyOfBookList));
    }
}
