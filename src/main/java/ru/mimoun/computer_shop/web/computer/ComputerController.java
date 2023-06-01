package ru.mimoun.computer_shop.web.computer;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mimoun.computer_shop.model.Computer;
import ru.mimoun.computer_shop.repository.ComputerRepository;
import ru.mimoun.computer_shop.to.ComputerTo;
import ru.mimoun.computer_shop.util.validation.ValidationUtil;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static ru.mimoun.computer_shop.web.computer.ComputerController.REST_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ComputerController {
    private final ComputerRepository computerRepository;
    static final String REST_URL = "/api/product/computers";

    @Operation(summary = "Get Computer by id")
    @GetMapping("/{id}")
    public Computer get(@PathVariable UUID id) {
        return computerRepository.getExisted(id);
    }

    @Operation(summary = "Get list of computer")
    @GetMapping
    public List<Computer> getAll() {
        return computerRepository.findAll(Sort.by(Sort.Direction.ASC, "manufacture"));
    }

    @Operation(summary = "Create computer")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Computer> createWithLocation(@Valid @RequestBody ComputerTo computer) {
        ValidationUtil.checkNew(computer);
        Computer toSave = new Computer(computer.getId(), computer.getManufacture(),
                                       computer.getSeries(), computer.getPrice(),
                                       computer.getAmount(), computer.getFormFactor());
        Computer created = computerRepository.save(toSave);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                          .path(REST_URL + "/{id}")
                                                          .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Operation(summary = "Update computer by id")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody ComputerTo computer, @PathVariable UUID id) {
        ValidationUtil.assureIdConsistent(computer, id);
        Computer toSave = new Computer(id, computer.getManufacture(),
                                       computer.getSeries(), computer.getPrice(),
                                       computer.getAmount(), computer.getFormFactor());
        computerRepository.save(toSave);
    }
}
