package com.woodwing.demo.domain;

public enum UnitType {
    meters("meters"),
    yards("yards");

    private String name;

    UnitType(String name) {
        this.name = name;
    }

    public static boolean contains(String name) {
        for (UnitType type : values()) {
            if (type.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }
}
