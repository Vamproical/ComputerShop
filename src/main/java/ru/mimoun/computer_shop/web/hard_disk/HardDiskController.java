package ru.mimoun.computer_shop.web.hard_disk;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mimoun.computer_shop.model.HardDisk;
import ru.mimoun.computer_shop.repository.HardDiskRepository;
import ru.mimoun.computer_shop.to.HardDiskTo;
import ru.mimoun.computer_shop.util.validation.ValidationUtil;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static ru.mimoun.computer_shop.web.hard_disk.HardDiskController.REST_URL;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HardDiskController {
    private final HardDiskRepository hardDiskRepository;
    static final String REST_URL = "/api/product/hard-disks";

    @Operation(summary = "Get Hard Disk by id")
    @GetMapping("/{id}")
    public HardDisk get(@PathVariable UUID id) {
        return hardDiskRepository.getExisted(id);
    }

    @Operation(summary = "Get list of hard disk")
    @GetMapping
    public List<HardDisk> getAll() {
        return hardDiskRepository.findAll(Sort.by(Sort.Direction.ASC, "manufacture"));
    }

    @Operation(summary = "Create hard disk")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HardDisk> createWithLocation(@Valid @RequestBody HardDiskTo hardDisk) {
        ValidationUtil.checkNew(hardDisk);
        HardDisk toSave = new HardDisk(null, hardDisk.getManufacture(),
                                       hardDisk.getSeries(), hardDisk.getPrice(),
                                       hardDisk.getAmount(), hardDisk.getVolume());
        HardDisk created = hardDiskRepository.save(toSave);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                          .path(REST_URL + "/{id}")
                                                          .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Operation(summary = "Update hard disk by id")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody HardDiskTo hardDisk, @PathVariable UUID id) {
        ValidationUtil.assureIdConsistent(hardDisk, id);
        HardDisk toSave = new HardDisk(id, hardDisk.getManufacture(),
                                       hardDisk.getSeries(), hardDisk.getPrice(),
                                       hardDisk.getAmount(), hardDisk.getVolume());
        hardDiskRepository.save(toSave);
    }
}
