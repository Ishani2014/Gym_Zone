package com.example.myapplication3345;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Add_Member extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        final EditText edit_name = findViewById(R.id.edt_Name);
        final EditText edit_position = findViewById(R.id.edt_Number);
        final EditText edit_email = findViewById(R.id.edt_Email);
        final EditText edit_address = findViewById(R.id.edt_Address);
        Button btn = findViewById(R.id.btn_submit);

        DAOEmployee dao =new DAOEmployee();
        Employee emp_edit = (Employee)getIntent().getSerializableExtra("EDIT");
        if(emp_edit !=null)
        {
            btn.setText("UPDATE");
            edit_name.setText(emp_edit.getName());
            edit_position.setText(emp_edit.getPosition());
            edit_email.setText(emp_edit.getEmail());
            edit_address.setText(emp_edit.getAddress());

        }
        else
        {
            btn.setText("SUBMIT");

        }
        btn.setOnClickListener(v->
        {
            Employee emp = new Employee(edit_name.getText().toString(), edit_position.getText().toString(), edit_email.getText().toString() , edit_address.getText().toString() );
            if(emp_edit==null)
            {
                dao.add(emp).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
            else
            {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", edit_name.getText().toString());
                hashMap.put("position", edit_position.getText().toString());
                hashMap.put("email", edit_email.getText().toString());
                hashMap.put("address",edit_address.getText().toString());
                dao.update(emp_edit.getKey(), hashMap).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Record is updated", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });


    }
}