package com.stehno.demo;

import org.apache.commons.lang3.builder.CompareToBuilder;

import static java.lang.String.format;

public class Name implements Comparable<Name> {

    private final String first;
    private final String last;

    public Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    @Override
    public String toString() {
        return format("Name{first='%s', last='%s'}", first, last);
    }

    public String toJson() {
        final StringBuffer str = new StringBuffer();

        str.append('{');
        str.append("\"first\":\"").append(first).append("\", ");
        str.append("\"last\":\"").append(last).append("\" ");
        str.append('}');

        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (!first.equals(name.first)) return false;
        return last.equals(name.last);
    }

    @Override
    public int hashCode() {
        int result = first.hashCode();
        result = 31 * result + last.hashCode();
        return result;
    }

    public int compareTo(final Name name) {
        return new CompareToBuilder().append(last, name.last).append(first, name.first).toComparison();
    }
}
