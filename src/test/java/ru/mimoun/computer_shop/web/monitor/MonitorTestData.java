package ru.mimoun.computer_shop.web.monitor;

import ru.mimoun.computer_shop.model.Monitor;
import ru.mimoun.computer_shop.to.MonitorTo;
import ru.mimoun.computer_shop.web.MatcherFactory;

import java.util.UUID;

public class MonitorTestData {
    public static final MatcherFactory.Matcher<Monitor> MONITOR_MATCHER = MatcherFactory.usingEqualsComparator(Monitor.class);
    public static final UUID MONITOR_1_ID = UUID.fromString("00000000-0000-0000-0000-000000000004");
    public static final UUID MONITOR_2_ID = UUID.fromString("00000000-0000-0000-0000-000000000005");
    public static final UUID NOT_FOUND = UUID.fromString("00000000-0000-0000-0000-000000000101");

    public static final Monitor monitor1 = new Monitor(MONITOR_1_ID, "LG", "D111AA", 1532, 15, 23.4);
    public static final Monitor monitor2 = new Monitor(MONITOR_2_ID, "Samsung", "D152AB", 2120, 20, 27.2);

    public static MonitorTo getNew() {
        return new MonitorTo(null, "newManufacture", "newSeries", 100, 1, 14.6);
    }

    public static Monitor getUpdated() {
        return new Monitor(MONITOR_1_ID, "updatedManufacture", "updatedSeries", 100, 1, 17.3);
    }

    public static MonitorTo getUpdatedTo() {
        return new MonitorTo(MONITOR_1_ID, "updatedManufacture", "updatedSeries", 100, 1, 17.3);
    }
}
