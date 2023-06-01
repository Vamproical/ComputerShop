package ru.mimoun.computer_shop.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.mimoun.computer_shop.model.ProductType;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductTo extends BaseTo {
    @NotBlank
    protected String manufacture;

    @NotBlank
    protected String series;

    @NotNull
    protected Integer price;

    @NotNull
    protected Integer amount;

    protected ProductType type;

    public ProductTo(UUID id, String manufacture, String series, @NotNull Integer price, @NotNull Integer amount, ProductType type) {
        super(id);

        this.manufacture = manufacture;
        this.series = series;
        this.price = price;
        this.amount = amount;
        this.type = type;
    }
}
