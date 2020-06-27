package com.example.rommieskatta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
FirebaseAuth mauth;
FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mauth=FirebaseAuth.getInstance();
        final EditText email=(EditText)findViewById(R.id.email);
        final EditText password=(EditText)findViewById(R.id.password);
        Button signin=(Button)findViewById(R.id.singin);
        Button signup=(Button)findViewById(R.id.signup);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EMAIL=email.getText().toString();
                String PASSWORD=password.getText().toString();
                mauth.signInWithEmailAndPassword(EMAIL,PASSWORD).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,"Login Sucessfull",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,MainData.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Email Or Password is Incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterUser.class);
                //intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            Intent intent=new Intent(MainActivity.this,MainData.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP&Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);

        }
    }
}