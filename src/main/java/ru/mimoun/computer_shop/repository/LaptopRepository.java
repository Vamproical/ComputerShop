package ru.mimoun.computer_shop.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.computer_shop.model.Laptop;

@Transactional(readOnly = true)
public interface LaptopRepository extends BaseRepository<Laptop> {
}
