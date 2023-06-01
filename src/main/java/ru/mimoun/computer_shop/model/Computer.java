package ru.mimoun.computer_shop.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "computer")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Computer extends ProductEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "form_factor", nullable = false)
    private FormFactorType formFactor;
}
