package ru.mimoun.computer_shop.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.mimoun.computer_shop.model.HardDisk;

@Transactional(readOnly = true)
public interface HardDiskRepository extends BaseRepository<HardDisk> {
}
