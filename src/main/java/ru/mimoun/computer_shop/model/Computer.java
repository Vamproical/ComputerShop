package ru.mimoun.computer_shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "computer")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Computer extends ProductEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "form_factor", nullable = false)
    private FormFactorType formFactor;

    public Computer(@NotNull UUID id, String manufacture, String series, @NotNull Integer price, @NotNull Integer amount, FormFactorType formFactor) {
        super(id, manufacture, series, price, amount, ProductType.PC);
        this.formFactor = formFactor;
    }
}
