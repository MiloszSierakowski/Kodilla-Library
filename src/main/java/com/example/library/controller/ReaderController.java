package com.example.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/reader")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReaderController {
    //todo    dodanie czytelnika
}
