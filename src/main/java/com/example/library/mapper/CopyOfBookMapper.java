package com.example.library.mapper;

import com.example.library.domain.CopyOfBook;
import com.example.library.domain.CopyOfBookDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CopyOfBookMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(ignore = true, target = "book")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "rented", target = "rented")
    @Mapping(ignore = true, target = "rentalList")
    CopyOfBook mapToCopyOfBook(CopyOfBookDto copyOfBookDto);

    @InheritInverseConfiguration(name = "mapToCopyOfBook")
    @Mapping(source = "book.id", target = "bookId")
    CopyOfBookDto mapToCopyOfBookDto(CopyOfBook copyOfBook);

    List<CopyOfBookDto> mapToListCopyOfBookDto(List<CopyOfBook> copyOfBookList);
    List<CopyOfBook> mapToListCopyOfBook(List<CopyOfBookDto> copyOfBookDtoList);
}
