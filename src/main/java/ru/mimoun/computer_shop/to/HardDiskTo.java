package ru.mimoun.computer_shop.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.mimoun.computer_shop.model.ProductType;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class HardDiskTo extends ProductTo {
    Integer volume;

    public HardDiskTo(UUID id, String manufacture, String series, @NotNull Integer price, @NotNull Integer amount, Integer volume) {
        super(id, manufacture, series, price, amount, ProductType.HARD_DISK);
        this.volume = volume;
    }
}
