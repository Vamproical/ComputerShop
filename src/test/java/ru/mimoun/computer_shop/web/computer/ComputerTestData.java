package ru.mimoun.computer_shop.web.computer;

import ru.mimoun.computer_shop.model.Computer;
import ru.mimoun.computer_shop.web.MatcherFactory;

public class ComputerTestData {
    public static final MatcherFactory.Matcher<Computer> USER_MATCHER = MatcherFactory.usingEqualsComparator(Computer.class);
}
