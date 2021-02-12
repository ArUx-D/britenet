package com.miszczuk.dawid.britenet.database;

import com.miszczuk.dawid.britenet.database.dto.ContactDTO;
import com.miszczuk.dawid.britenet.database.dto.PersonDTO;
import com.miszczuk.dawid.britenet.model.Person;

import java.sql.*;
import java.util.List;

import static java.lang.String.format;

public class MySqlConnector {

    // JDBC driver name and database URL
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/zadanie_rekrutacyjne?serverTimezone=UTC";

    //  Database credentials
    private static final String USER = "admin";
    private static final String PASS = "admin";
    public static final String CUSTOMER_INSERT_QUERY_FORMAT = "INSERT INTO customers(NAME,SURNAME,AGE) VALUES('%s','%s','%s');";
    public static final String CONTACT_INSERT_QUERY_FORMAT = "INSERT INTO contacts(ID_CUSTOMER,TYPE,CONTACT) VALUES('%s','%s','%s');";


    public void savePersonToDatabase(PersonDTO person) {
        final long customerId = insertPersonToDatabase(person);
        if (!(person.getContactDTO() == null)) {
            insertContactToDatabase(person.getContactDTO(), customerId);
        }
    }

    private long insertPersonToDatabase(PersonDTO personDTO) {
        final String customerQuery = prepareQueryStringForCustomer(personDTO);
        long personId = -1;
        try (Connection conn = DriverManager.getConnection(connectionUrl, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement(customerQuery,Statement.RETURN_GENERATED_KEYS);
            ps.execute();
            final int idValueIndex = 1;
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            personId = generatedKeys.getLong(idValueIndex);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personId;
    }

    private void insertContactToDatabase(List<ContactDTO> contacts, Long customerId) {
        for (ContactDTO contact : contacts) {
            final String query = prepareQueryStringForContact(contact, customerId);
            try (Connection conn = DriverManager.getConnection(connectionUrl, USER, PASS)) {
                PreparedStatement ps = conn.prepareStatement(query);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String prepareQueryStringForCustomer(PersonDTO person) {
        return format(CUSTOMER_INSERT_QUERY_FORMAT, person.getName(), person.getSurname(), person.getAge());
    }

    private String prepareQueryStringForContact(ContactDTO contact, long customerId) {
        return format(CONTACT_INSERT_QUERY_FORMAT, customerId, contact.getType(), contact.getContactValue());
    }

}
