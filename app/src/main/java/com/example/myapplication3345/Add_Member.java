package com.example.myapplication3345;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class Add_Member extends AppCompatActivity {

    AutoCompleteTextView atv;
    String[] plan = new String[]{"Silver", "Gold", "Platinum"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        atv = findViewById(R.id.atv);
        atv.setAdapter(new ArrayAdapter<>(Add_Member.this, android.R.layout.simple_list_item_1, plan));
        final EditText edit_name = findViewById(R.id.edt_Name);
        final EditText edit_position = findViewById(R.id.edt_Number);
        final EditText edit_email = findViewById(R.id.edt_Email);
        final EditText edit_address = findViewById(R.id.edt_Address);
        final EditText edit_paln = findViewById(R.id.atv);
        Button btn = findViewById(R.id.btn_submit);
        DAOEmployee dao = new DAOEmployee();
        Employee emp_edit = (Employee) getIntent().getSerializableExtra("EDIT");
        if (emp_edit != null) {
            btn.setText("UPDATE");
            edit_name.setText(emp_edit.getName());
            edit_position.setText(emp_edit.getPosition());
            edit_email.setText(emp_edit.getEmail());
            edit_address.setText(emp_edit.getAddress());
            edit_paln.setText(emp_edit.getPlan());

        } else {
            btn.setText("SUBMIT");

        }
        btn.setOnClickListener(v ->
        {
            Employee emp = new Employee(edit_name.getText().toString(), edit_position.getText().toString(), edit_email.getText().toString(), edit_address.getText().toString(), edit_paln.getText().toString());
            if (emp_edit == null) {
                dao.add(emp).addOnSuccessListener(suc ->
                {
                    Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er ->
                {
                    Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            } else {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("name", edit_name.getText().toString());
                hashMap.put("position", edit_position.getText().toString());
                hashMap.put("email", edit_email.getText().toString());
                hashMap.put("address", edit_address.getText().toString());
                hashMap.put("plan",edit_paln.getText().toString());
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