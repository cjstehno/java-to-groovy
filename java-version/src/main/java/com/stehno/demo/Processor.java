package com.stehno.demo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static com.stehno.demo.Gender.fromAbbrev;
import static java.lang.Long.parseLong;
import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.FileUtils.readLines;
import static org.apache.commons.io.FileUtils.write;

public class Processor {

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("M/d/yyyy");

    public static void main(final String[] args) {
        final File csvFile = new File(args[0]);
        final File outputDir = new File(args[1]);

        Processor processor = new Processor();
        processor.process(csvFile, outputDir);
    }

    void process(final File csvFile, final File outputDir) {
        try {
            List<String> lines = readLines(csvFile, defaultCharset());

            // skip header line
            lines.remove(0);

            final Set<Person> people = new TreeSet<Person>();

            // load the data (id,first,last,dob,gender)
            for (final String line : lines) {
                final String[] row = line.split(",");

                people.add(new Person(
                    parseLong(row[0]),
                    new Name(row[1], row[2]),
                    dateFormatter.parse(row[3]),
                    fromAbbrev(row[4])
                ));
            }

            // ensure output dir exists
            outputDir.mkdirs();

            // write out one json file / last name

            String currentLastName = null;
            File ouputFile = null;

            for (final Person person : people) {
                final String lastName = person.getName().getLast();

                if (currentLastName == null || !currentLastName.equals(lastName)) {
                    if (currentLastName != null) {
                        // close off the old file json array
                        write(ouputFile, "]", defaultCharset(), true);
                    }

                    currentLastName = lastName;

                    ouputFile = new File(outputDir, lastName.toLowerCase() + ".json");
                    ouputFile.createNewFile();

                    // open the json array
                    write(ouputFile, "[", defaultCharset(), true);
                }

                write(ouputFile, person.toJson(), defaultCharset(), true);
            }

            // close out the last file
            write(ouputFile, "]", defaultCharset(), true);

            System.out.println("Done");

        } catch (Exception ex) {
            // FIXME: logging
            ex.printStackTrace();
        }
    }
}
