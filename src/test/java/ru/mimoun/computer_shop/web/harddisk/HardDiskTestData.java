package ru.mimoun.computer_shop.web.harddisk;

import ru.mimoun.computer_shop.model.HardDisk;
import ru.mimoun.computer_shop.to.HardDiskTo;
import ru.mimoun.computer_shop.web.MatcherFactory;

import java.util.UUID;

public class HardDiskTestData {
    public static final MatcherFactory.Matcher<HardDisk> HARD_DISK_MATCHER = MatcherFactory.usingEqualsComparator(HardDisk.class);
    public static final UUID HARD_DISK_1_ID = UUID.fromString("00000000-0000-0000-0000-000000000006");
    public static final UUID HARD_DISK_2_ID = UUID.fromString("00000000-0000-0000-0000-000000000007");
    public static final UUID NOT_FOUND = UUID.fromString("00000000-0000-0000-0000-000000000101");

    public static final HardDisk hardDisk1 = new HardDisk(HARD_DISK_1_ID, "SanDisk", "H152FB", 100, 50, 1024);
    public static final HardDisk hardDisk2 = new HardDisk(HARD_DISK_2_ID, "WD", "H152MB", 250, 150, 2048);

    public static HardDiskTo getNew() {
        return new HardDiskTo(null, "newManufacture", "newSeries", 100, 1, 512);
    }

    public static HardDisk getUpdated() {
        return new HardDisk(HARD_DISK_1_ID, "updatedManufacture", "updatedSeries", 100, 1, 512);
    }

    public static HardDiskTo getUpdatedTo() {
        return new HardDiskTo(HARD_DISK_1_ID, "updatedManufacture", "updatedSeries", 100, 1, 512);
    }
}
