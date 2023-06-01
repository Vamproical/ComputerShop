package ru.mimoun.computer_shop.web.laptop;

import ru.mimoun.computer_shop.model.Laptop;
import ru.mimoun.computer_shop.model.LaptopSizeType;
import ru.mimoun.computer_shop.to.LaptopTo;
import ru.mimoun.computer_shop.web.MatcherFactory;

import java.util.UUID;

public class LaptopTestData {
    public static final MatcherFactory.Matcher<Laptop> LAPTOP_MATCHER = MatcherFactory.usingEqualsComparator(Laptop.class);
    public static final UUID LAPTOP_1_ID = UUID.fromString("00000000-0000-0000-0000-000000000002");
    public static final UUID LAPTOP_2_ID = UUID.fromString("00000000-0000-0000-0000-000000000003");
    public static final UUID NOT_FOUND = UUID.fromString("00000000-0000-0000-0000-000000000101");

    public static final Laptop laptop1 = new Laptop(LAPTOP_1_ID, "Asus", "L152FB", 850, 8, LaptopSizeType.LAPTOP_13);
    public static final Laptop laptop2 = new Laptop(LAPTOP_2_ID, "Microsoft", "L192FO", 2000, 10, LaptopSizeType.LAPTOP_17);

    public static LaptopTo getNew() {
        return new LaptopTo(null, "newManufacture", "newSeries", 100, 1, 14);
    }

    public static Laptop getUpdated() {
        return new Laptop(LAPTOP_1_ID, "updatedManufacture", "updatedSeries", 100, 1, LaptopSizeType.LAPTOP_14);
    }

    public static LaptopTo getUpdatedTo() {
        return new LaptopTo(LAPTOP_1_ID, "updatedManufacture", "updatedSeries", 100, 1, 14);
    }
}
