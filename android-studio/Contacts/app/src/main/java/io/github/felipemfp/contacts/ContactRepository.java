package io.github.felipemfp.contacts;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by felipe on 18/04/17.
 */

public class ContactRepository extends HashMap<String, Contact> {

    private static final long serialVersionUID = 1L;

    public Contact store(String name, String phone) {
        Contact contact = new Contact(name, phone);
        this.put(contact.getId(), contact);
        return contact;
    }

    public boolean update(String id, String name, String phone) {
        Contact contact = this.get(id);
        if (contact == null) {
            return false;
        }
        contact.setName(name);
        contact.setPhone(phone);
        this.put(contact.getId(), contact);
        return true;
    }

    public boolean delete(String id) {
        if (!this.containsKey(id)) {
            return false;
        }
        this.remove(id);
        return true;
    }

    public ArrayList<Contact> asArray() {
        return new ArrayList<Contact>(this.values());
    }
}
