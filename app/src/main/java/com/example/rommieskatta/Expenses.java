package com.example.rommieskatta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Expenses extends AppCompatActivity {
  FirebaseDatabase database=FirebaseDatabase.getInstance();
  DatabaseReference reference=database.getReference();
  DatabaseReference ref=reference.child("Expenses");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        final EditText name=(EditText)findViewById(R.id.ExpenName);
        final EditText details=(EditText)findViewById(R.id.Expendetail);
        final EditText amount=(EditText)findViewById(R.id.ExpenAmount);
        final EditText date=(EditText)findViewById(R.id.Date);
        Button add=(Button)findViewById(R.id.AddExpenses);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=name.getText().toString();
                String Details=details.getText().toString();
                String Amount=amount.getText().toString();
                String Date=date.getText().toString();
                ExpensesData data=new ExpensesData(Name,Details,Amount,Date);
                ref.child(Name).setValue(data);
                Toast.makeText(Expenses.this,"Your Expenses added succesfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}