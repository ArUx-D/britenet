package com.miszczuk.dawid.britenet.services.parser.csv;

import com.miszczuk.dawid.britenet.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.*;
import static java.lang.System.*;
import static java.util.Collections.*;

public class CsvParserService {
    public static final String CSV_SUFFIX = ".txt";

    public static final String INVALID_FORMAT_MSG = "Not an CSV file!";
    public static final String PARSING_ERROR_MSG = "Unexpected error while parsing CSV file!";

    public static final int NAME_INDEX = 0;
    public static final int SURNAME_INDEX = 1;
    public static final int AGE_INDEX = 2;
    public static final int CITY_INDEX = 3;
    public static final int EXTRA_INFORMATION_STARTING_INDEX = 4;

    public List<Person> parseCsv(String path) {
        if (!path.endsWith(CSV_SUFFIX)) {
            out.println(INVALID_FORMAT_MSG);
            return emptyList();
        }

        List<Person> personList = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(path))) {
            final int numberOfLinesToSkip = 1;
            csvReader.skip(numberOfLinesToSkip);
            String[] values = csvReader.readNext();
            while (values != null) {
                Person person = createPersonFromLine(values);
                addExtraInformation(values, person);
                personList.add(person);
                values = csvReader.readNext();
            }
        } catch (CsvValidationException | IOException e) {
            out.println(PARSING_ERROR_MSG);;
        }
        return personList;
    }

    private void addExtraInformation(String[] values, Person person) {
        try {
            for (int i = EXTRA_INFORMATION_STARTING_INDEX; ; i++) {
                person.addExtraInformation(values[i]);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private Person createPersonFromLine(String[] values) {
        return new Person(
                values[NAME_INDEX],
                values[SURNAME_INDEX],
                parseInt(values[AGE_INDEX]),
                values[CITY_INDEX]);
    }
}
