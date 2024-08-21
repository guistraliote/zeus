package net.guistraliote.zeus.internaluser;

import org.springframework.stereotype.Component;

@Component
public class InternalUserMapper {

    public InternalUserDTO toDto (InternalUser entity) {
        return InternalUserDTO.builder()
                .withName(entity.getName())
                .withEmail(entity.getEmail())
                .withPassword(entity.getPassword())
                .build();
    }

    public InternalUser toEntity(InternalUserDTO dto) {
        return InternalUser.builder()
                .withName(dto.getName())
                .withEmail(dto.getEmail())
                .withPassword(dto.getPassword())
                .build();
    }
}
