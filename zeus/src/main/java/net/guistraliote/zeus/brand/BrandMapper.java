package net.guistraliote.zeus.brand;

import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public BrandDTO toDto(Brand brand) {
        return BrandDTO.builder()
                .withName(brand.getName())
                .withDescription(brand.getDescription())
                .build();
    }

    public Brand toEntity(BrandDTO dto) {
        return Brand.builder()
                .withName(dto.getName())
                .withDescription(dto.getDescription())
                .build();
    }
}
