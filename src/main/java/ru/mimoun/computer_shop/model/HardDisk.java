package ru.mimoun.computer_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hard-disk")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HardDisk extends ProductEntity {
    @NotNull
    @Column(name = "volume", nullable = false)
    private Integer volume;
}
