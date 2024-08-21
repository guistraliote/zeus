package net.guistraliote.zeus.category;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryDTO dto) {
        return Category.builder()
                .withName(dto.getName())
                .withLevel(dto.getLevel())
                .withPath(dto.getPath())
                .build();
    }

    public CategoryDTO toDto(Category category) {
        return CategoryDTO.builder()
                .withName(category.getName())
                .withLevel(category.getLevel())
                .withPath(category.getPath())
                .build();
    }
}
