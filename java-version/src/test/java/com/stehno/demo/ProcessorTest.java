package com.stehno.demo;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static java.nio.charset.Charset.defaultCharset;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProcessorTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void simple_run() throws Exception {
        File input = new File(ProcessorTest.class.getResource("/test-01.csv").toURI());

        Processor processor = new Processor();
        processor.process(input, folder.getRoot());

        File stehnos = new File(folder.getRoot(), "stehno.json");
        assertTrue(stehnos.exists());

        assertEquals(
            "[{\"id\":101, \"name\":{\"first\":\"Chris\", \"last\":\"Stehno\" }, \"dob\":\"01/28/1973\", \"gender\":\"M\"}]",
            readFileToString(stehnos, defaultCharset())
        );

        File ablemans = new File(folder.getRoot(), "ableman.json");
        assertTrue(ablemans.exists());

        assertEquals(
            "[{\"id\":100, \"name\":{\"first\":\"Abe\", \"last\":\"Ableman\" }, \"dob\":\"04/15/2001\", \"gender\":\"M\"}{\"id\":102, \"name\":{\"first\":\"Jessica\", \"last\":\"Ableman\" }, \"dob\":\"07/29/2003\", \"gender\":\"F\"}]",
            readFileToString(ablemans, defaultCharset())
        );
    }
}