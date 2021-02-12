package com.miszczuk.dawid.britenet.services.parser.dtos;

import com.miszczuk.dawid.britenet.database.dto.ContactDTO;
import com.miszczuk.dawid.britenet.model.Contact;
import com.miszczuk.dawid.britenet.services.ContactService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ContactDTOService {
    private ContactService contactService;

    public List<ContactDTO> mapToContactDtos(List<String> extraInformationList) {
        if (extraInformationList == null) {
            return null;
        }

        List<ContactDTO> contactDTOS = new ArrayList<>();
        for (String contactString : extraInformationList) {
            final Contact contact = contactService.mapToContact(contactString);
            contactDTOS.add(new ContactDTO(null, null, contact.getType(), contact.getValue()));
        }
        return contactDTOS;
    }

}
