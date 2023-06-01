package ru.mimoun.computer_shop.web.monitor;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.mimoun.computer_shop.model.Monitor;
import ru.mimoun.computer_shop.repository.MonitorRepository;
import ru.mimoun.computer_shop.to.MonitorTo;
import ru.mimoun.computer_shop.util.validation.ValidationUtil;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static ru.mimoun.computer_shop.web.monitor.MonitorController.REST_URL;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MonitorController {
    private final MonitorRepository monitorRepository;
    static final String REST_URL = "/api/product/monitors";

    @Operation(summary = "Get monitor by id")
    @GetMapping("/{id}")
    public Monitor get(@PathVariable UUID id) {
        return monitorRepository.getExisted(id);
    }

    @Operation(summary = "Get list of monitor")
    @GetMapping
    public List<Monitor> getAll() {
        return monitorRepository.findAll(Sort.by(Sort.Direction.ASC, "manufacture"));
    }

    @Operation(summary = "Create monitor")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Monitor> createWithLocation(@Valid @RequestBody MonitorTo monitor) {
        ValidationUtil.checkNew(monitor);
        Monitor toSave = new Monitor(null, monitor.getManufacture(),
                                     monitor.getSeries(), monitor.getPrice(),
                                     monitor.getAmount(), monitor.getDiagonal());
        Monitor created = monitorRepository.save(toSave);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                          .path(REST_URL + "/{id}")
                                                          .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Operation(summary = "Update monitor by id")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody MonitorTo monitor, @PathVariable UUID id) {
        ValidationUtil.assureIdConsistent(monitor, id);
        Monitor toSave = new Monitor(id, monitor.getManufacture(),
                                     monitor.getSeries(), monitor.getPrice(),
                                     monitor.getAmount(), monitor.getDiagonal());
        monitorRepository.save(toSave);
    }
}
