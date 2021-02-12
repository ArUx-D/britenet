package com.miszczuk.dawid.britenet.services.parser.xml;

import com.miszczuk.dawid.britenet.model.Person;
import com.miszczuk.dawid.britenet.services.parser.xml.jackson.PersonHolder;
import com.miszczuk.dawid.britenet.services.parser.xml.jackson.PersonSerializer;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
import static java.util.Collections.emptyList;

@Data
public class XMLParserService {
    public static final String XML_SUFFIX = ".xml";
    public static final String INVALID_FORMAT_MSG = "Not an XML file!";
    public static final String PARSING_ERROR_MSG = "Unexpected error while parsing XML file!";

    private PersonSerializer personSerializer;

    public XMLParserService(PersonSerializer personSerializer) {
        this.personSerializer = personSerializer;
    }

    public List<Person> parseXml(String path) {
        if (!path.endsWith(XML_SUFFIX)) {
            out.println(INVALID_FORMAT_MSG);
            return emptyList();
        }
        List<Person> personList = new ArrayList<>();
        try {
            final PersonHolder deserializePersons = personSerializer.deserialize(path);
            personList = deserializePersons.getItem();
        } catch (IOException e) {
            out.println(PARSING_ERROR_MSG);
        }

        return personList;
    }
}
