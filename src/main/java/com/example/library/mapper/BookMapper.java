package com.example.library.mapper;

import com.example.library.domain.Book;
import com.example.library.domain.BookDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "publicationDate", target = "publicationDate")
    @Mapping(ignore = true, target = "copyOfBookList")
    Book mapToBook(BookDto bookDto);

    @InheritInverseConfiguration(name = "mapToBook")
    BookDto mapToBookDto(Book book);
}
