package com.miszczuk.dawid.britenet.services.parser.dtos;

import com.miszczuk.dawid.britenet.database.dto.PersonDTO;
import com.miszczuk.dawid.britenet.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDTOService {
    private ContactDTOService contactDTOService;

    public PersonDTO mapToPersonDto(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName());
        personDTO.setSurname(person.getSurname());
        personDTO.setAge(String.valueOf(person.getAge()));
        personDTO.setContactDTO(contactDTOService.mapToContactDtos(person.getExtraInformationList()));
        return personDTO;
    }
}
