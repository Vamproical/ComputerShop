package ru.mimoun.computer_shop.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.computer_shop.model.Monitor;

@Transactional(readOnly = true)
public interface MonitorRepository extends BaseRepository<Monitor> {
}
