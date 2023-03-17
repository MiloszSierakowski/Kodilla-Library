package com.example.library.mapper;

import com.example.library.domain.Reader;
import com.example.library.domain.ReaderDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReaderMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "registrationDate", target = "registrationDate")
    @Mapping(ignore = true, target = "rentalList")
    Reader mapToReader(ReaderDto readerDto);

    @InheritInverseConfiguration(name = "mapToReader")
    ReaderDto mapToReaderDto(Reader reader);

}
