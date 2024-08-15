package net.guistraliote.zeus.brand;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BrandService {

    private static final String NOT_FOUND_ERROR_MESSAGE = "Não foi encontrada a marca de id: %s";
    private static final String NAME_NOT_FOUND_ERROR_MESSAGE = "Não foi encontrada a marca: %s";
    private static final String DUPLICATED_BRAND_ERROR_MESSAGE = "A marca %s já está cadastrada no sitema";

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public Page<Brand> findAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return brandRepository.findAll(pageable);
    }

    public BrandDTO findByName(String name) {
        Optional<Brand> brand = brandRepository.findBrandByName(name);

        if (brand.isEmpty()) {
            throw new EntityNotFoundException(String.format(NAME_NOT_FOUND_ERROR_MESSAGE, name));
        }

        return brandMapper.toDto(brand.get());
    }

    @Transactional
    public BrandDTO save(BrandDTO dto) {
        Optional<Brand> existingBrand = brandRepository.findBrandByName(dto.getName());

        if (existingBrand.isPresent()) {
            throw new RuntimeException(String.format(DUPLICATED_BRAND_ERROR_MESSAGE, dto.getName()));
        }

        Brand brand = brandMapper.toEntity(dto);
        brand.setCreatedAt(LocalDateTime.now());

        return brandMapper.toDto(brandRepository.save(brand));
    }

    @Transactional
    public BrandDTO update(Long id, BrandDTO brandDTO) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_ERROR_MESSAGE, id)));

        Brand brandToUpdate = Brand.builder()
                .withId(existingBrand.getId())
                .withName(validateBrandName(brandDTO, existingBrand))
                .withDescription(validateBrandDescription(brandDTO, existingBrand))
                .withCreatedAt(existingBrand.getCreatedAt())
                .withUpdateAt(LocalDateTime.now())
                .build();

        Brand savedBrand = brandRepository.save(brandToUpdate);

        return brandMapper.toDto(savedBrand);
    }

    public void delete(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_ERROR_MESSAGE, id)));

        if (nonNull(brand)) {
            brandRepository.delete(brand);
        }
    }

    private String validateBrandDescription(BrandDTO brandDTO, Brand existingBrand) {
        return nonNull(brandDTO.getDescription()) ? brandDTO.getDescription() : existingBrand.getDescription();
    }

    private String validateBrandName(BrandDTO brandDTO, Brand existingBrand) {
        return nonNull(brandDTO.getName()) ? brandDTO.getName() : existingBrand.getName();
    }
}
