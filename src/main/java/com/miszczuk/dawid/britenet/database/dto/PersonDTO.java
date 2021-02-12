package com.miszczuk.dawid.britenet.database.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {
    private String name;
    private String surname;
    private String age;
    List<ContactDTO> contactDTO;
}
