package com.example.rommieskatta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {

    FirebaseAuth mauth;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference reference=database.getReference();
    DatabaseReference ref=reference.child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mauth=FirebaseAuth.getInstance();
        final EditText name=(EditText)findViewById(R.id.name);
        final EditText surname=(EditText)findViewById(R.id.surname);
        final EditText email=(EditText)findViewById(R.id.emailr);
        final EditText password=(EditText)findViewById(R.id.passwordr);
        Button signup=(Button)findViewById(R.id.register);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Name=name.getText().toString();
                final String SurName=surname.getText().toString();
                final String Email=email.getText().toString();
                String Password=password.getText().toString();
                mauth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(RegisterUser.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Users users=new Users(Name,SurName,Email);
                            ref.push().setValue(users);
                            Toast.makeText(RegisterUser.this,"Register Sussesfull",Toast.LENGTH_SHORT).show();;
                        }
                        else {
                            Toast.makeText(RegisterUser.this,"Register failed",Toast.LENGTH_SHORT).show();;

                        }
                    }
                });
            }
        });
    }
}