package com.example.library.mapper;

import com.example.library.domain.CopyOfBook;
import com.example.library.domain.CopyOfBookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CopyOfBookMapper {
    public CopyOfBook mapToCopyOfBook(CopyOfBookDto copyOfBookDto) {
        return new CopyOfBook(copyOfBookDto.getId(), copyOfBookDto.getBook(),
                copyOfBookDto.getStatus(), copyOfBookDto.isRental(), copyOfBookDto.getRentalList());
    }

    public CopyOfBookDto mapToCopyOfBookDto(CopyOfBook copyOfBook) {
        return new CopyOfBookDto(copyOfBook.getId(), copyOfBook.getBook(),
                copyOfBook.getStatus(), copyOfBook.isRental(), copyOfBook.getRentalList());
    }

    public List<CopyOfBookDto> mapToListCopyOfBookDto(List<CopyOfBook> copyOfBookList) {
        return copyOfBookList.stream().map(this::mapToCopyOfBookDto).toList();
    }
}
