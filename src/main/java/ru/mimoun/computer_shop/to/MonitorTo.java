package ru.mimoun.computer_shop.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.mimoun.computer_shop.model.ProductType;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class MonitorTo extends ProductTo {
    Double diagonal;

    public MonitorTo(UUID id, String manufacture, String series, @NotNull Integer price, @NotNull Integer amount, Double diagonal) {
        super(id, manufacture, series, price, amount, ProductType.MONITOR);
        this.diagonal = diagonal;
    }
}
