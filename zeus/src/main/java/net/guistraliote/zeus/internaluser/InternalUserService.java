package net.guistraliote.zeus.internaluser;

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

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InternalUserService {

    private static final String NOT_FOUND_ERROR_MESSAGE = "Não foi encontrada o usuário de id: %s";
    private static final String NAME_NOT_FOUND_ERROR_MESSAGE = "Não foi encontrada o usuário com o email: %s";
    private static final String DUPLICATED_INTERNALUSER_ERROR_MESSAGE = "O usuário %s já está cadastrada no sitema";

    private final InternalUserRepository internalUserRepository;
    private final InternalUserMapper internalUserMapper;

    public Page<InternalUser> findAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return internalUserRepository.findAll(pageable);
    }

    public InternalUserDTO findByEmail(String email) {
        Optional<InternalUser> internalUser = internalUserRepository.findInternalUserByEmail(email);

        if (internalUser.isEmpty()) {
            throw new EntityNotFoundException(String.format(NAME_NOT_FOUND_ERROR_MESSAGE, email));
        }

        return internalUserMapper.toDto(internalUser.get());
    }

    @Transactional
    public InternalUserDTO save(InternalUserDTO dto) {
        Optional<InternalUser> existingInternalUser = internalUserRepository.findInternalUserByEmail(dto.getEmail());

        if (existingInternalUser.isPresent()) {
            throw new RuntimeException(String.format(DUPLICATED_INTERNALUSER_ERROR_MESSAGE, dto.getEmail()));
        }

        InternalUser internalUser = internalUserMapper.toEntity(dto);
        internalUser.setCreatedAt(LocalDateTime.now());

        return internalUserMapper.toDto(internalUserRepository.save(internalUser));
    }

    @Transactional
    public InternalUserDTO update(Long id, InternalUserDTO internalUserDTO) {
        InternalUser existingInternalUser = internalUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_ERROR_MESSAGE, id)));

        InternalUser internalUserToUpdate = InternalUser.builder()
                .withId(existingInternalUser.getId())
                .withName(internalUserDTO.getName())
                .withEmail(validateInternalUserEmail(internalUserDTO, existingInternalUser))
                .withPassword(validateInternalUserPassword(internalUserDTO, existingInternalUser))
                .withCreatedAt(existingInternalUser.getCreatedAt())
                .withUpdatedAt(LocalDateTime.now())
                .build();

        InternalUser savedInternalUser = internalUserRepository.save(internalUserToUpdate);

        return internalUserMapper.toDto(savedInternalUser);
    }

    public void delete(Long id) {
        InternalUser internalUser = internalUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NOT_FOUND_ERROR_MESSAGE, id)));

        if (nonNull(internalUser)) {
            internalUserRepository.delete(internalUser);
        }
    }

    private String validateInternalUserEmail(InternalUserDTO internalUserDTO, InternalUser existingInternalUser) {
        return nonNull(internalUserDTO.getEmail()) ? internalUserDTO.getEmail() : existingInternalUser.getEmail();
    }

    private String validateInternalUserPassword(InternalUserDTO internalUserDTO, InternalUser existingInternalUser) {
        return nonNull(internalUserDTO.getPassword()) ? internalUserDTO.getPassword() : existingInternalUser.getPassword();
    }
}
