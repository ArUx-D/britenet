package com.miszczuk.dawid.britenet;

import com.miszczuk.dawid.britenet.database.MySqlConnector;
import com.miszczuk.dawid.britenet.database.dto.PersonDTO;
import com.miszczuk.dawid.britenet.model.Person;
import com.miszczuk.dawid.britenet.services.ContactService;
import com.miszczuk.dawid.britenet.services.parser.csv.CsvParserService;
import com.miszczuk.dawid.britenet.services.parser.dtos.ContactDTOService;
import com.miszczuk.dawid.britenet.services.parser.dtos.PersonDTOService;
import com.miszczuk.dawid.britenet.services.parser.xml.XMLParserService;
import com.miszczuk.dawid.britenet.services.parser.xml.jackson.PersonHolder;
import com.miszczuk.dawid.britenet.services.parser.xml.jackson.PersonSerializer;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.miszczuk.dawid.britenet.services.parser.csv.CsvParserService.*;
import static com.miszczuk.dawid.britenet.services.parser.xml.XMLParserService.*;

public class Main {

    static CsvParserService csvParserService = new CsvParserService();
    static XMLParserService xmlParserService = new XMLParserService(new PersonSerializer());
    static MySqlConnector mySqlConnector = new MySqlConnector();
    static PersonDTOService personDTOService = new PersonDTOService(new ContactDTOService(new ContactService()));

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(args));
        String filePath = "";
        if (args.length > 0) {
            filePath = args[0];
        }

        final List<Person> personListFromFile = getPersonListFromFile(filePath);
        if (!personListFromFile.isEmpty() && !filePath.isEmpty()) {
            save(personListFromFile);
        } else {
            System.out.println("Sorry, can't find given file. Make sure You provided valid path for Your file!");
        }

    }

    public static void save(List<Person> personList) {
        for (Person person : personList) {
            final PersonDTO personDTO = personDTOService.mapToPersonDto(person);
            mySqlConnector.savePersonToDatabase(personDTO);
        }
    }

    public static List<Person> getPersonListFromFile(String filePath) {
        List<Person> personList = new ArrayList<>();

        if (filePath.endsWith(CSV_SUFFIX)) {
            return csvParserService.parseCsv(filePath);
        } else if (filePath.endsWith(XML_SUFFIX)) {
            return xmlParserService.parseXml(filePath);
        } else {
            return personList;
        }
    }
}
