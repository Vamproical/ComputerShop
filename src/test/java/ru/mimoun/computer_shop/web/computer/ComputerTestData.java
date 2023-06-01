package ru.mimoun.computer_shop.web.computer;

import ru.mimoun.computer_shop.model.Computer;
import ru.mimoun.computer_shop.model.FormFactorType;
import ru.mimoun.computer_shop.to.ComputerTo;
import ru.mimoun.computer_shop.web.MatcherFactory;

import java.util.UUID;

public class ComputerTestData {
    public static final MatcherFactory.Matcher<Computer> COMPUTER_MATCHER = MatcherFactory.usingEqualsComparator(Computer.class);
    public static final UUID COMPUTER_1_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");
    public static final UUID COMPUTER_2_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");
    public static final UUID NOT_FOUND = UUID.fromString("00000000-0000-0000-0000-000000000101");

    public static final Computer computer1 = new Computer(COMPUTER_1_ID, "Apple", "X302LA", 1250, 3, FormFactorType.DESKTOP);
    public static final Computer computer2 = new Computer(COMPUTER_2_ID, "Samsung", "X712LZ", 500, 1, FormFactorType.NETTOP);

    public static ComputerTo getNew() {
        return new ComputerTo(null, "newManufacture", "newSeries", 100, 1, FormFactorType.MONOBLOCK);
    }

    public static Computer getUpdated() {
        return new Computer(COMPUTER_1_ID, "updatedManufacture", "updatedSeries", 100, 1, FormFactorType.MONOBLOCK);
    }

    public static ComputerTo getUpdatedTo() {
        return new ComputerTo(COMPUTER_1_ID, "updatedManufacture", "updatedSeries", 100, 1, FormFactorType.MONOBLOCK);
    }
}
