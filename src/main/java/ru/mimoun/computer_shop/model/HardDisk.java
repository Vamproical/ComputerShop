package ru.mimoun.computer_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "hard_disk")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HardDisk extends ProductEntity {
    @Column(name = "volume", nullable = false)
    private Integer volume;

    public HardDisk(UUID id, String manufacture, String series, @NotNull Integer price, @NotNull Integer amount, Integer volume) {
        super(id, manufacture, series, price, amount, ProductType.HARD_DISK);
        this.volume = volume;
    }
}
