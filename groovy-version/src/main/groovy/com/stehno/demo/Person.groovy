package com.stehno.demo

import groovy.json.JsonOutput
import groovy.transform.Canonical
import groovy.transform.Sortable

@Canonical @Sortable(includes = ['name'])
class Person {

    Long id
    Name name
    Date dateOfBirth
    Gender gender

    String toJson() {
        JsonOutput.toJson(this)
    }
}
