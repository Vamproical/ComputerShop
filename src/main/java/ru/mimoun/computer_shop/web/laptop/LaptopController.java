package ru.mimoun.computer_shop.web.laptop;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mimoun.computer_shop.model.Laptop;
import ru.mimoun.computer_shop.repository.LaptopRepository;
import ru.mimoun.computer_shop.to.LaptopTo;
import ru.mimoun.computer_shop.util.validation.ValidationUtil;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static ru.mimoun.computer_shop.web.laptop.LaptopController.REST_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class LaptopController {
    private final LaptopRepository laptopRepository;
    static final String REST_URL = "/api/product/laptops";

    @Operation(summary = "Get laptop by id")
    @GetMapping("/{id}")
    public Laptop get(@PathVariable UUID id) {
        return laptopRepository.getExisted(id);
    }

    @Operation(summary = "Get list of laptop")
    @GetMapping
    public List<Laptop> getAll() {
        return laptopRepository.findAll(Sort.by(Sort.Direction.ASC, "manufacture"));
    }

    @Operation(summary = "Create laptop")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Laptop> createWithLocation(@Valid @RequestBody LaptopTo laptop) {
        ValidationUtil.checkNew(laptop);
        Laptop toSave = new Laptop(null, laptop.getManufacture(),
                                   laptop.getSeries(), laptop.getPrice(),
                                   laptop.getAmount(), laptop.getSize());
        Laptop created = laptopRepository.save(toSave);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                          .path(REST_URL + "/{id}")
                                                          .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Operation(summary = "Update laptop by id")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody LaptopTo laptop, @PathVariable UUID id) {
        ValidationUtil.assureIdConsistent(laptop, id);
        Laptop toSave = new Laptop(id, laptop.getManufacture(),
                                   laptop.getSeries(), laptop.getPrice(),
                                   laptop.getAmount(), laptop.getSize());
        laptopRepository.save(toSave);
    }
}
