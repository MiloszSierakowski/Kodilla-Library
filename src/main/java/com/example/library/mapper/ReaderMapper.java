package com.example.library.mapper;

import com.example.library.domain.Reader;
import com.example.library.domain.ReaderDto;
import org.springframework.stereotype.Service;

@Service
public class ReaderMapper {
    public Reader mapToReader(ReaderDto readerDto) {
        return new Reader(readerDto.getId(), readerDto.getFirstname(),
                readerDto.getLastname(), readerDto.getRegistrationDate());
    }

    public ReaderDto mapToBookDto(Reader reader) {
        return new ReaderDto(reader.getId(), reader.getFirstname(),
                reader.getLastname(), reader.getRegistrationDate());
    }
}
