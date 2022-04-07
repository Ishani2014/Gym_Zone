package com.example.myapplication3345;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class  SignupActivity extends AppCompatActivity {
    EditText name, email, contact, password, confirmPassword, dob;
    Button  signup;
    ImageView dobIv;
    Spinner spinner, state;
    ArrayList<String> arrayList;
    ArrayList<String> stateArrayList;
    RadioGroup gender;

    Calendar calendar;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,10}";
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Signup");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = findViewById(R.id.etname);
        email = findViewById(R.id.etemail);
        contact = findViewById(R.id.etcont);
        password = findViewById(R.id.sign_password);
        confirmPassword = findViewById(R.id.sign_conpassword);
        dob = findViewById(R.id.etdob);
        dobIv = findViewById(R.id.ivdob);
        signup = findViewById(R.id.btsignup);
        gender = findViewById(R.id.gender);
        state = findViewById(R.id.spinner_state);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance("https://gymzone-5a983-default-rtdb.firebaseio.com/");
        databaseReference = firebaseDatabase.getReference("SignUp");

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = gender.getCheckedRadioButtonId(); //R.id.signup_male
                RadioButton rb = findViewById(id);
                new Comman_Method(SignupActivity.this, rb.getText().toString());
            }
        });
        calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                dob.setText(dateFormat.format(calendar.getTime()));
            }
        };
        dobIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(SignupActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialog.show();
            }
        });
        stateArrayList = new ArrayList<>();
        stateArrayList.add("Gujarat");
        stateArrayList.add("Rajasthan");
        stateArrayList.add("Maharastra");
        ArrayAdapter stateAdapter = new ArrayAdapter(SignupActivity.this, android.R.layout.simple_list_item_1, stateArrayList){

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView tvData = (TextView) super.getView(position, convertView, parent);
                tvData.setTextColor(Color.WHITE);
                return tvData;
            }
        };
        state.setAdapter(stateAdapter);
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              //  String s = stateArrayList.get(i);
                //new Comman_Method(SignupActivity.this, s);
                //cityDataSet(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim().equalsIgnoreCase("")) {
                    name.setError("Name Required");
                } else if (email.getText().toString().trim().equalsIgnoreCase("")) {
                    email.setError("Email Id Required");
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Valid Email Id Required");
                } else if (contact.getText().toString().trim().equalsIgnoreCase("")) {
                    contact.setError("Contact No. Required");
                } else if (contact.getText().toString().length() < 10) {
                    contact.setError("Valid Contact No. Required");
                } else if (password.getText().toString().trim().equalsIgnoreCase("")) {
                    password.setError("Password Required");
               /* } else if (!password.getText().toString().trim().matches(passwordPattern)) {
                    password.setError("Strong Password Required");*/
                } else if (confirmPassword.getText().toString().trim().equalsIgnoreCase("")) {
                    confirmPassword.setError("Confirm Password Required");
                } else if (!confirmPassword.getText().toString().matches(password.getText().toString())) {
                    confirmPassword.setError("Password Does Not Match");
                } else if (dob.getText().toString().trim().equalsIgnoreCase("")) {
                    dob.setError("Date Of Birth Required");
                } else if (gender.getCheckedRadioButtonId() == -1) {
                    new Comman_Method(SignupActivity.this, "Please Select Gender");
                } else {


                    int genderID = gender.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(genderID);
                    String strEmail = email.getText().toString();
                    String strPassword = confirmPassword.getText().toString();
                    String strName = name.getText().toString();
                    String strContact = contact.getText().toString();
                    String strDOB = dob.getText().toString();
                    String strGender = radioButton.getText().toString();

                    firebaseAuth.createUserWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                String uID = firebaseAuth.getUid();
                                Log.e("SIGNUP_ACTIVITY", "onComplete: "+uID );
                                new Comman_Method(SignupActivity.this, "Signup Successfully");
                                Toast.makeText(SignupActivity.this, ""+uID, Toast.LENGTH_SHORT).show();
                                SignUpModel  registerModel = new SignUpModel();
                                registerModel.setUserId(uID);
                                registerModel.setUserName(strName);
                                registerModel.setEmail(strEmail);
                                registerModel.setPassword(strPassword);
                                registerModel.setContactNo(strContact);
                                registerModel.setUserDOB(strDOB);
                                databaseReference.child(uID).setValue(registerModel);
                                Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                }
            }


        });

    }
    private void cityDataSet(int i) {
        arrayList = new ArrayList<>();
        if (i == 0) {
            arrayList.add("Ahemebad");
            arrayList.add("Gandhinagar");
            arrayList.add("Rajkot");
            arrayList.add("Test");
            arrayList.add("Surat");
            arrayList.add("Vadodara");
            arrayList.set(0, "Ahmedabad");
            arrayList.remove(3);
        }
        if (i == 1) {
            arrayList.add("Kota");
            arrayList.add("Jaipur");
        }
        if (i == 2) {
            arrayList.add("Mumbai");
            arrayList.add("Pune");
        }
        ArrayAdapter adapter = new ArrayAdapter(SignupActivity.this, android.R.layout.simple_list_item_1, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s = arrayList.get(i);
                new Comman_Method(SignupActivity.this, s);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void ShowHidePass(View view) {

        if (view.getId() == R.id.show_pass_btn){

            if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (view)).setImageResource(R.drawable.ic_baseline_visibility_off_24);

                //Show Password
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (view)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);

                //Hide Password
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }
    public void ShowHideconPass(View v){
        if (v.getId() == R.id.show_conpass_btn){

            if (password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                ((ImageView) (v)).setImageResource(R.drawable.ic_baseline_visibility_off_24);

                //Show Password
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                ((ImageView) (v)).setImageResource(R.drawable.ic_baseline_remove_red_eye_24);

                //Hide Password
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }

    }


    }
