package com.miszczuk.dawid.britenet.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
    private String type;
    private String value;
}
