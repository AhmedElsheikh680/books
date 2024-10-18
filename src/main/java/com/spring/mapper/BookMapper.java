package com.spring.mapper;


import com.spring.dto.BookDTO;
import com.spring.entity.Book;
import com.spring.mapper.qualifier.BookFavorateQualifierImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(uses = {AuthorMapper.class, BookFavorateQualifierImpl.class})
//@Mapper(uses = {AuthorMapper.class}, componentModel = "spring")
public interface BookMapper {


//    @Mapping(source = "author", target = "authorDTO")
    @Mapping(target = "authorDTO", ignore = true)
    @Mapping(source = "author.fullName", target = "authorName")
    @Mapping(source = "author.email", target = "authorEmail")
//    @Mapping(source = ".", target = "isFavorate", qualifiedBy = {BookFavorateQualifier.class, BookFavorateQualifier.BookFavorateFlagMethodQualifier.class})
    BookDTO map(Book entity);

//    @Mapping(source = "authorDTO", target = "author")
@Mapping(target = "author.fullName", source = "authorName")
@Mapping(target = "author.email", source = "authorEmail")
    Book unMap(BookDTO dto);
}
