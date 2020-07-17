package com.example.assignment2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText etfirstName, etlastname, etphoneNumber,etemail,etaddress;
    Button update_button, delete_button;

    String id, firstName , lastname, phone,email,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etfirstName = findViewById(R.id.etfirstName2);
        etlastname = findViewById(R.id.etlastName2);
        etphoneNumber = findViewById(R.id.etphoneNumber2);
        etemail = findViewById(R.id.etEmail2);
        etaddress = findViewById(R.id.etAddress2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(firstName);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                firstName = etfirstName.getText().toString().trim();
                lastname = etlastname.getText().toString().trim();
                phone = etphoneNumber.getText().toString().trim();
                email = etemail.getText().toString().trim();
                address = etaddress.getText().toString().trim();
                myDB.updateData(id, firstName, lastname,phone,email,address);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("firstName") &&
                getIntent().hasExtra("lastname") && getIntent().hasExtra("phone") && getIntent().hasExtra("email") && getIntent().hasExtra("address")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            firstName = getIntent().getStringExtra("firstName");
            lastname = getIntent().getStringExtra("lastname");
            phone = getIntent().getStringExtra("phone");
            email = getIntent().getStringExtra("email");
            address = getIntent().getStringExtra("address");

            //Setting Intent Data
            etfirstName.setText(firstName);
            etlastname.setText(lastname);
            etphoneNumber.setText(phone);
            etemail.setText(email);
            etaddress.setText(address);
            Log.d("", firstName+" "+lastname+" "+phone+" "+email+" "+address);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + firstName + " ?");
        builder.setMessage("Are you sure you want to delete " + firstName + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
