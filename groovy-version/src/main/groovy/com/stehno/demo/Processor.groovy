package com.stehno.demo

import static com.stehno.demo.Gender.fromAbbrev

class Processor {

    static void main(final String[] args) {
        File csvFile = new File(args[0])
        File outputDir = new File(args[1])

        Processor processor = new Processor()
        processor.process(csvFile, outputDir)
    }

    void process(final File csvFile, final File outputDir) {
        try {
            List<String> lines = csvFile.readLines()

            // skip header line
            lines.remove(0)

            Set<Person> people = new TreeSet<Person>()

            // load the data (id,first,last,dob,gender)
            lines.each { String line ->
                def (id, first, last, dob, gender) = line.split(',')

                people << new Person(
                    id as long,
                    new Name(first, last),
                    Date.parse('M/d/yyyy', dob),
                    fromAbbrev(gender)
                )
            }

            // ensure output dir exists
            outputDir.mkdirs()

            // write out one json file / last name

            String currentLastName = null
            File outputFile = null

            people.each { Person person ->
                String lastName = person.name.last

                if (!currentLastName || currentLastName != lastName) {
                    if (currentLastName) {
                        // close off the old file json array
                        outputFile << ']'
                    }

                    currentLastName = lastName

                    outputFile = new File(outputDir,  "${lastName.toLowerCase()}.json")
                    outputFile.createNewFile()

                    // open the json array
                    outputFile << '['
                }

                outputFile << person.toJson()
            }

            // close out the last file
            outputFile << ']'

            println 'Done'

        } catch (Exception ex) {
            ex.printStackTrace()
        }
    }
}
