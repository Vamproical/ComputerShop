package ru.mimoun.computer_shop.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.mimoun.computer_shop.model.ProductType;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class LaptopTo extends ProductTo {
    Integer size;

    public LaptopTo(UUID id, String manufacture, String series, @NotNull Integer price, @NotNull Integer amount, Integer size) {
        super(id, manufacture, series, price, amount, ProductType.LAPTOP);
        this.size = size;
    }
}
