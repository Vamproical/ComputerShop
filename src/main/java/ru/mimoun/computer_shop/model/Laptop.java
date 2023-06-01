package ru.mimoun.computer_shop.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "laptop")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Laptop extends ProductEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private LaptopSizeType size;
}
