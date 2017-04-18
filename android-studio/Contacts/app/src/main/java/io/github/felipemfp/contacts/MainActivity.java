package io.github.felipemfp.contacts;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;

    private Button insertButton;
    private Button updateButton;
    private Button deleteButton;

    private ListView contactsListView;
    private ArrayAdapter<Contact> adapter;

    private Contact currentContact;
    private ContactRepository contactsRepo = new ContactRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        phoneEditText = (EditText) findViewById(R.id.phoneEditText);

        insertButton = (Button) findViewById(R.id.insertButton);
        updateButton = (Button) findViewById(R.id.updateButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);


        contactsListView = (ListView) findViewById(R.id.contactsListView);

        adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contactsRepo.asArray());
        contactsListView.setAdapter(adapter);
        contactsListView.setOnItemClickListener(handleListItemClick);
    }

    private AdapterView.OnItemClickListener handleListItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            currentContact = contactsRepo.asArray().get(position);
            nameEditText.setText(currentContact.getName());
            phoneEditText.setText(currentContact.getPhone());
        }
    };

    private void clear() {
        nameEditText.setText("");
        phoneEditText.setText("");
        currentContact = null;
    }

    private void update() {
        adapter.clear();
        adapter.addAll(contactsRepo.asArray());
    }

    private boolean validate(String name, String phone) {
        return validate(name, phone, false);
    }

    private boolean validate(String name, String phone, boolean isInsert) {
        if (name.isEmpty()) {
            return false;
        }
        if (phone.isEmpty()) {
            return false;
        }
        if (isInsert && currentContact != null)
        {
            if (currentContact.getName().equals(name) && currentContact.getPhone().equals((phone))) {
                return false;
            }
        }
        return true;
    }

    private void toast(int messageId) {
        Context context = getApplicationContext();
        String message = context.getString(messageId);
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void handleInsertButtonClick(View view) {
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        if (!validate(name, phone, true)) {
            toast(R.string.insert_failure_message);
            return;
        }
        contactsRepo.store(name, phone);
        clear();
        update();
        toast(R.string.insert_success_message);
    }

    public void handleUpdateButtonClick(View view) {
        if (currentContact == null) {
            toast(R.string.update_error_message);
        }
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        if (!validate(name, phone)) {
            toast(R.string.update_failure_message);
            return;
        }
        contactsRepo.update(currentContact.getId(), name, phone);
        clear();
        update();
        toast(R.string.update_success_message);
    }

    public void handleDeleteButtonClick(View view) {
        if (currentContact == null) {
            toast(R.string.delete_error_message);
            return;
        }
        contactsRepo.delete(currentContact.getId());
        clear();
        update();
        toast(R.string.delete_success_message);
    }
}
