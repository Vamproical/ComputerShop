package ru.mimoun.computer_shop.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.computer_shop.model.Computer;

@Transactional(readOnly = true)
public interface ComputerRepository extends BaseRepository<Computer> {
}
