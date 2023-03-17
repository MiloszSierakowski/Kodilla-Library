package com.example.library.controller;

import com.example.library.domain.Reader;
import com.example.library.domain.ReaderDto;
import com.example.library.mapper.LocalDateAdapter;
import com.example.library.mapper.ReaderMapper;
import com.example.library.service.ReaderService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(ReaderController.class)
class ReaderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ReaderMapper readerMapper;
    @MockBean
    private ReaderService readerService;

    @Test
    void createNewReader() throws Exception {
        Reader reader = new Reader(1L, "Milosz", "S", LocalDate.now(), new ArrayList<>());
        ReaderDto readerDto = new ReaderDto(1L, "Milosz", "S", LocalDate.now());

        when(readerMapper.mapToReader(any(ReaderDto.class))).thenReturn(reader);
        when(readerService.saveReader(any(Reader.class))).thenReturn(reader);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        String jsonContent = gson.toJson(readerDto);

        mockMvc.
                perform(MockMvcRequestBuilders
                        .post("/v1/reader")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}