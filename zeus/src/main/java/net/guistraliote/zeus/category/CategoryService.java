package net.guistraliote.zeus.category;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public Page<Category> findAllWithHierarchy(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories = categoryRepository.findAll(pageable);

        categories.forEach(category -> {
            if (isNull(category.getParent())) {
                List<Category> children = categoryRepository.findByParentId(category.getId());
                category.setChildren(children);
            }
        });

        return categories;
    }

    @Transactional
    public CategoryDTO save(CategoryDTO dto) {
        var parent = getParentId(dto);
        if (parent.isPresent()) {
            return saveWithParent(dto);
        }

        return saveWithoutParent(dto);
    }

    private CategoryDTO saveWithoutParent(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    private CategoryDTO saveWithParent(CategoryDTO dto) {
        Category category = categoryMapper.toEntity(dto);
        category.setParent(null);
        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    private Optional<Category> getParentId(CategoryDTO dto) {
        if (nonNull(dto.getParentId())) {
            return categoryRepository.findById(dto.getParentId());
        }

        return Optional.empty();
    }
}
