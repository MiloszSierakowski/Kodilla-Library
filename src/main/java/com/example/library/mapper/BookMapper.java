package com.example.library.mapper;

import com.example.library.domain.Book;
import com.example.library.domain.BookDto;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public Book mapToBook(BookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getTitle(),
                bookDto.getAuthor(), bookDto.getPublicationDate(), bookDto.getCopyOfBookList());
    }

    public BookDto mapToBookDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(),
                book.getAuthor(), book.getPublicationDate(), book.getCopyOfBookList());
    }

}
