package ru.mimoun.computer_shop.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.mimoun.computer_shop.model.FormFactorType;
import ru.mimoun.computer_shop.model.ProductType;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class ComputerTo extends ProductTo {
    FormFactorType formFactor;

    public ComputerTo(UUID id, String manufacture, String series, @NotNull Integer price, @NotNull Integer amount, FormFactorType formFactor) {
        super(id, manufacture, series, price, amount, ProductType.PC);
        this.formFactor = formFactor;
    }
}
