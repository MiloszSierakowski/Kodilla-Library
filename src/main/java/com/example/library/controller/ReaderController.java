package com.example.library.controller;

import com.example.library.domain.Reader;
import com.example.library.domain.ReaderDto;
import com.example.library.mapper.ReaderMapper;
import com.example.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reader")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReaderController {
    private final ReaderService readerService;
    private final ReaderMapper readerMapper;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        readerService.saveReader(reader);
        return ResponseEntity.ok().build();
    }
}
