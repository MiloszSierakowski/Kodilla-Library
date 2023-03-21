package com.example.library.controller;

import com.example.library.domain.Book;
import com.example.library.domain.CopyOfBook;
import com.example.library.domain.CopyOfBookDto;
import com.example.library.mapper.CopyOfBookMapper;
import com.example.library.service.BookService;
import com.example.library.service.CopyOfBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/copyOfBook")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CopyOfBookController {
    private final CopyOfBookService copyOfBookService;
    private final CopyOfBookMapper copyOfBookMapper;
    private final BookService bookService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewCopyOfBook(@RequestBody CopyOfBookDto copyOfBookDto) throws BookNotFoundException {
        CopyOfBook copyOfBook = copyOfBookMapper.mapToCopyOfBook(copyOfBookDto);
        Book book = bookService.findById(copyOfBookDto.getBookId());

        copyOfBook.setBook(book);
        copyOfBookService.saveCopyOfBook(copyOfBook);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{id}/{status}")
    public ResponseEntity<CopyOfBookDto> changeCopyOfBookStatus(@PathVariable Long id, @PathVariable String status) throws CopyOfBookNotFoundException {
        CopyOfBook copyOfBook = copyOfBookService.findById(id);
        copyOfBook.setStatus(status);
        copyOfBookService.saveCopyOfBook(copyOfBook);
        return ResponseEntity.ok(copyOfBookMapper.mapToCopyOfBookDto(copyOfBook));
    }

    @GetMapping(value = "/{title}")
    public ResponseEntity<List<CopyOfBookDto>> howManyCopiesAreAvailable(@PathVariable String title) {
        List<CopyOfBook> copyOfBookList = copyOfBookService.findAvailableCopyOfBook(title);
        return ResponseEntity.ok(copyOfBookMapper.mapToListCopyOfBookDto(copyOfBookList));
    }
}
