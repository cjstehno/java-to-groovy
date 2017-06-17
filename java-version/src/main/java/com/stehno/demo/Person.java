package com.stehno.demo;

import org.apache.commons.lang3.builder.CompareToBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

public class Person implements Comparable<Person> {

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
    private Long id;
    private Name name;
    private Date dateOfBirth;
    private Gender gender;

    public Person(Long id, Name name, Date dateOfBirth, Gender gender) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return format("Person{id=%d, name=%s, dateOfBirth=%s, gender=%s}", id, name, dateOfBirth, gender);
    }

    public String toJson() {
        final StringBuffer str = new StringBuffer();

        str.append('{');

        str.append("\"id\":").append(id).append(", ");
        str.append("\"name\":").append(name.toJson()).append(", ");
        str.append("\"dob\":\"").append(dateFormatter.format(dateOfBirth)).append("\", ");
        str.append("\"gender\":\"").append(gender).append("\"");

        str.append('}');

        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!id.equals(person.id)) return false;
        if (!name.equals(person.name)) return false;
        if (!dateOfBirth.equals(person.dateOfBirth)) return false;
        return gender == person.gender;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + gender.hashCode();
        return result;
    }

    public int compareTo(final Person person) {
        return new CompareToBuilder().append(name, person.name).toComparison();
    }
}
