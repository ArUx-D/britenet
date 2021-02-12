package com.miszczuk.dawid.britenet.services;

import com.miszczuk.dawid.britenet.model.Contact;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data


public class ContactService {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PHONE_REGEX =
            Pattern.compile("^((\\\\(\\\\d{3}\\\\))|\\\\d{3})[- .]?\\\\d{3}[- .]?\\\\d{4}$");
    public static final Pattern JABBER_REGEX =
            Pattern.compile("^(?:([^@/<>'\\\"]+)@)?([^@/<>'\\\"]+)(?:/([^<>'\\\"]*))?$");


    public static final String UNKNOWN = "0";
    public static final String EMAIL = "1";
    public static final String PHONE = "2";
    public static final String JABBER = "3";


    public Contact mapToContact(String contact) {
        return new Contact(getContactType(contact), contact);
    }

    private String getContactType(String contact) {
        if (isEmail(contact)) {
            return EMAIL;
        } else if (isPhoneNumber(contact)) {
            return PHONE;
        } else if (isJabber(contact)) {
            return JABBER;
        }
        return UNKNOWN;

    }

    private boolean isEmail(String contact) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(contact);
        return matcher.find();
    }

    private boolean isPhoneNumber(String contact) {
        Matcher matcher = VALID_PHONE_REGEX.matcher(contact);
        return matcher.find();
    }

    private boolean isJabber(String contact) {
        Matcher matcher = JABBER_REGEX.matcher(contact);
        return matcher.find();
    }
}
