package com.miszczuk.dawid.britenet.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactDTO {
    Long id;
    Long customerId;
    String type;
    String contactValue;
}
