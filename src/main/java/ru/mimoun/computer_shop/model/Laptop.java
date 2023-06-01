package ru.mimoun.computer_shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "laptop")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Laptop extends ProductEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private LaptopSizeType size;

    public Laptop(UUID id, String manufacture, String series, @NotNull Integer price, @NotNull Integer amount, LaptopSizeType size) {
        super(id, manufacture, series, price, amount, ProductType.LAPTOP);
        this.size = size;
    }
}
