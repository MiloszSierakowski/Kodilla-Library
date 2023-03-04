package com.example.library.controller;

import com.example.library.mapper.CopyOfBookMapper;
import com.example.library.service.CopyOfBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/copyOfBook")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CopyOfBookController {

    private final CopyOfBookService copyOfBookService;
    private final CopyOfBookMapper copyOfBookMapper;

    //todo    dodanie egzemplarza

    //todo    zmiana statusu egzemplarza

    //todo    sprawdzenie ilości egzemplarzy danego tytułu dostępnych do wypożyczenia
}
