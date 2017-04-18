package io.github.felipemfp.contacts;

import java.util.UUID;

/**
 * Created by felipe on 18/04/17.
 */

public class Contact {

    private String id;
    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + "\n" + phone;
    }

}
