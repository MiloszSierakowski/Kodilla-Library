package com.example.library.mapper;

import com.example.library.domain.Rental;
import com.example.library.domain.RentalDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RentalMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(ignore = true, target = "copyOfBook")
    @Mapping(ignore = true, target = "reader")
    @Mapping(source = "rentDate", target = "rentDate")
    @Mapping(source = "returnDate", target = "returnDate")
    Rental mapToRental(RentalDto rentalDto);

    @InheritInverseConfiguration(name = "mapToRental")
    @Mapping(source = "copyOfBook.id", target = "copyOfBookId")
    @Mapping(source = "reader.id", target = "readerId")
    RentalDto mapToRentalDto(Rental rental);

    List<RentalDto> mapToListRentalDto(List<Rental> rentalList);
    List<Rental> mapToListRental(List<RentalDto> rentalDtoList);

}
