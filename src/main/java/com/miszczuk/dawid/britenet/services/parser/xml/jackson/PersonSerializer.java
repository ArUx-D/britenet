package com.miszczuk.dawid.britenet.services.parser.xml.jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.miszczuk.dawid.britenet.model.Person;
import lombok.Data;

import java.io.File;
import java.io.IOException;
@Data
public class PersonSerializer {
    private XmlMapper xmlMapper = new XmlMapper();


    public PersonHolder deserialize(String filePath) throws IOException {
        return xmlMapper.readValue(new File(filePath), PersonHolder.class);
    }
}
