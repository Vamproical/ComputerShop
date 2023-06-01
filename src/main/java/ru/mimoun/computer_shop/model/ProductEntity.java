package ru.mimoun.computer_shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mimoun.computer_shop.HasId;

import java.util.UUID;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ProductEntity extends BaseEntity implements HasId {

    @NotBlank
    @Column(name = "manufacture", nullable = false)
    protected String manufacture;

    @NotBlank
    @Column(name = "series", nullable = false)
    protected String series;

    @NotNull
    @Column(name = "price", nullable = false)
    protected Integer price;

    @NotNull
    @Column(name = "amount", nullable = false)
    protected Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    protected ProductType type;


    protected ProductEntity(@NotNull UUID id, String manufacture, String series, @NotNull Integer price, @NotNull Integer amount, ProductType type) {
        super(id);
        this.manufacture = manufacture;
        this.series = series;
        this.price = price;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString()
               + '[' + manufacture + ']'
               + '[' + series + ']'
               + '[' + price + ']'
               + '[' + amount + ']'
               + '[' + type + ']';
    }
}