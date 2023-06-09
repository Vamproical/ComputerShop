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
@Table(name = "monitor")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Monitor extends ProductEntity {
    @NotNull
    @Column(name = "diagonal", nullable = false)
    private Double diagonal;

    public Monitor(UUID id, String manufacture, String series, @NotNull Integer price, @NotNull Integer amount, @NotNull Double diagonal) {
        super(id, manufacture, series, price, amount, ProductType.MONITOR);
        this.diagonal = diagonal;
    }
}
