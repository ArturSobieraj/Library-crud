package com.library.mappers;

import com.library.domain.HiredBooks;
import com.library.domain.dto.HiredBooksDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HiredBooksMapper {

    public HiredBooks mapToHiredBooks(HiredBooksDto hiredBooksDto) {
        return new HiredBooks(
              hiredBooksDto.getHireId(),
              hiredBooksDto.getBookCopies(),
              hiredBooksDto.getUser(),
              hiredBooksDto.getDateOfRent(),
              hiredBooksDto.getDateOfReturn());
    }

    public HiredBooksDto mapToHiredBooksDto(HiredBooks hiredBooks) {
        return new HiredBooksDto(
                hiredBooks.getId(),
                hiredBooks.getBookCopy(),
                hiredBooks.getUser(),
                hiredBooks.getDateOfRent(),
                hiredBooks.getDateOfReturn());
    }

    public List<HiredBooksDto> mapToHiredBooksDtoList(List<HiredBooks> hiredBooksList) {
        return hiredBooksList.stream()
                .map(hiredBooks -> new HiredBooksDto(
                        hiredBooks.getId(),
                        hiredBooks.getBookCopy(),
                        hiredBooks.getUser(),
                        hiredBooks.getDateOfRent(),
                        hiredBooks.getDateOfReturn()))
                .collect(Collectors.toList());
    }
}
