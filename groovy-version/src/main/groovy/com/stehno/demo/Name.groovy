package com.stehno.demo

import groovy.json.JsonOutput
import groovy.transform.Immutable
import groovy.transform.Sortable

@Immutable @Sortable(includes = ['last', 'first'])
class Name {

    String first
    String last

    String toJson() {
        JsonOutput.toJson(this)
    }
}
