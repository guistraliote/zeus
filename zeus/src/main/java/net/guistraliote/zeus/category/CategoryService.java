package net.guistraliote.zeus.category;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDTO save(CategoryDTO dto) {
        var parent = getParentId(dto);
        if (parent.isPresent()) {
            return saveWithParent(dto);
        }

       return saveWithoutParent(dto);
    }

    private CategoryDTO saveWithoutParent(CategoryDTO dto) {
        return dto;
    }

    private CategoryDTO saveWithParent(CategoryDTO dto) {
        return dto;
    }

    private Optional<Category> getParentId(CategoryDTO dto) {
        if (nonNull(dto.getParentId())) {
            return categoryRepository.findById(dto.getParentId());
        }

        return Optional.empty();
    }
}
