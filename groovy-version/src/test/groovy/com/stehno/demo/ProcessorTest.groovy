package com.stehno.demo

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class ProcessorTest extends Specification {

    @Rule public TemporaryFolder folder = new TemporaryFolder()

    def 'simple_run'() {
        setup:
        File input = new File(ProcessorTest.class.getResource("/test-01.csv").toURI())

        Processor processor = new Processor()

        when:
        processor.process(input, folder.getRoot())

        then:
        File stehnos = new File(folder.getRoot(), "stehno.json")
        stehnos.exists()

        stehnos.text == '[{"gender":"MALE","id":101,"dateOfBirth":"1973-01-28T06:00:00+0000","name":{"first":"Chris","last":"Stehno"}}]'

        File ablemans = new File(folder.getRoot(), "ableman.json")
        ablemans.exists()

        ablemans.text == '[{"gender":"MALE","id":100,"dateOfBirth":"2001-04-15T05:00:00+0000","name":{"first":"Abe","last":"Ableman"}}{"gender":"FEMALE","id":102,"dateOfBirth":"2003-07-29T05:00:00+0000","name":{"first":"Jessica","last":"Ableman"}}]'
    }
}
