package ru.mimoun.computer_shop.model;

public enum LaptopSizeType {
    LAPTOP_13(13),
    LAPTOP_14(14),
    LAPTOP_15(15),
    LAPTOP_17(17);

    private final int size;

    LaptopSizeType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
