package com.stehno.demo

import groovy.transform.TupleConstructor

@TupleConstructor
enum Gender {

    MALE('M'), FEMALE('F'), OTHER('O')

    final String abbrev

    static Gender fromAbbrev(final String a) {
        def gender = values().find { g -> g.abbrev.equalsIgnoreCase(a) }
        if (gender) {
            return gender
        } else {
            throw new IllegalArgumentException("Abbreviation '$a' does not match any gender")
        }
    }

    @Override
    String toString() {
        abbrev
    }
}
