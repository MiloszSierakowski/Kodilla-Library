package com.example.library.mapper;

import com.example.library.domain.CopyOfBook;
import com.example.library.domain.CopyOfBookDto;
import org.springframework.stereotype.Service;

@Service
public class CopyOfBookMapper {
    public CopyOfBook mapToCopyOfBook(CopyOfBookDto copyOfBookDto) {
        return new CopyOfBook(copyOfBookDto.getId(), copyOfBookDto.getBookId(),
                copyOfBookDto.getStatus(), copyOfBookDto.isRental());
    }

    public CopyOfBookDto mapToCopyOfBookDto(CopyOfBook copyOfBook) {
        return new CopyOfBookDto(copyOfBook.getId(), copyOfBook.getBookId(),
                copyOfBook.getStatus(), copyOfBook.isRental());
    }
}
