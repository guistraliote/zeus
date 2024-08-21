package net.guistraliote.zeus.internaluser;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/internalUser")
public class InternalUserController {

    private final InternalUserService internalUserService;

    @GetMapping
    public Page<InternalUser> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return internalUserService.findAllPaged(page, size);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<InternalUserDTO> findByName(@RequestParam String email) {
        return ResponseEntity.ok(internalUserService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<InternalUserDTO> save(@RequestBody InternalUserDTO dto) {
        InternalUserDTO internalUserToSave = internalUserService.save(dto);
        return ResponseEntity.ok(internalUserToSave);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<InternalUserDTO> update(@PathVariable Long id,
                                                  @RequestBody InternalUserDTO dto) {
        return ResponseEntity.ok(internalUserService.update(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<InternalUserDTO> delete(@PathVariable Long id) {
        internalUserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
