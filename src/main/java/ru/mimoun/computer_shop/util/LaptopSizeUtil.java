package ru.mimoun.computer_shop.util;

import lombok.experimental.UtilityClass;
import ru.mimoun.computer_shop.error.NotFoundException;
import ru.mimoun.computer_shop.model.LaptopSizeType;

import java.util.Arrays;

@UtilityClass
public class LaptopSizeUtil {
    public static LaptopSizeType getSize(int size, String msg) {
        return Arrays.stream(LaptopSizeType.values())
                     .filter(p -> p.getSize() == size)
                     .findFirst()
                     .orElseThrow(() -> new NotFoundException(msg));
    }
}
