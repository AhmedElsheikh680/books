package com.spring.mapper;

import com.spring.dto.AuthorDTO;
import com.spring.entity.Author;
import org.mapstruct.*;

import org.springframework.context.i18n.LocaleContextHolder;

//@Mapper(componentModel = "spring")
@Mapper(imports = LocaleContextHolder.class)
public interface AuthorMapper {

    // convert from entity to dto
//    @Mapping(source = "fullName", target="name")
    @Mappings({
//            @Mapping(source = "fullName", target = "name"),
            @Mapping(target = "name", expression = "java(LocaleContextHolder.getLocale().getLanguage() == \"ar\" ? author.getFullName() : author.getFullName())"),
//            @Mapping(target = "ipAddress", ignore = true)
            @Mapping(target = "ipAddress", defaultValue = "192.168.1.1")
    })
//    AuthorDTO mapToDTO(Author author);
    AuthorDTO map(Author author);

    // convert from dto to entity
    @Mapping(target = "fullName", source = "name")
//    Author mapToEntity(AuthorDTO authorDTO);
    Author unMap(AuthorDTO authorDTO);

    @AfterMapping
    default void mapName(Author author, @MappingTarget AuthorDTO authorDTO) {
        if (author.getFullName() != null) {
            String lang = LocaleContextHolder.getLocale().getLanguage();
            authorDTO.setName(lang.equals("ar") ? author.getFullName() : author.getFullName());
        }
    }

}
