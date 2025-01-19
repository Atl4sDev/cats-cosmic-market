package edu.catscosmicmarket.mappers;

import edu.catscosmicmarket.DTO.CategoryDTO;
import edu.catscosmicmarket.domain.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
