package com.example.assignment2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText etfirstName, etlastName, etphoneNumber, etEmail, etAddress;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etfirstName = findViewById(R.id.etfirstName);
        etlastName = findViewById(R.id.etlastName);
        etphoneNumber = findViewById(R.id.etphoneNumber);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addContact(etfirstName.getText().toString().trim(),
                        etlastName.getText().toString().trim(),
                        Integer.valueOf(etphoneNumber.getText().toString().trim()),
                        etEmail.getText().toString().trim(),
                        etAddress.getText().toString().trim());
            }
        });
    }
}
