package net.guistraliote.zeus.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "/brands")
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public Page<Brand> getAllPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return brandService.findAllPaged(page, size);
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<BrandDTO> findByName(@RequestParam String name) {
        return ResponseEntity.ok(brandService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<BrandDTO> save(@RequestBody BrandDTO dto) {
        BrandDTO brandToSave = brandService.save(dto);
        return ResponseEntity.ok(brandToSave);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BrandDTO> update(@PathVariable Long id,
                                           @RequestBody BrandDTO dto) {
        return ResponseEntity.ok(brandService.update(id, dto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BrandDTO> delete(@PathVariable Long id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
