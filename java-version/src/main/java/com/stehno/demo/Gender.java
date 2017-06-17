package com.stehno.demo;

public enum Gender {

    MALE("M"), FEMALE("F"), OTHER("O");

    private final String abbrev;

    private Gender(final String abbrev) {
        this.abbrev = abbrev;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public static Gender fromAbbrev(final String a) {
        for (final Gender gender : values()) {
            if (gender.abbrev.equalsIgnoreCase(a)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Abbreviation '" + a + "' does not match any gender");
    }

    @Override
    public String toString() {
        return abbrev;
    }
}
