package com.miszczuk.dawid.britenet.services.parser.xml.jackson;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.miszczuk.dawid.britenet.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonHolder {
    @JacksonXmlProperty(localName = "person")
    @JacksonXmlCData
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Person> item;
}
