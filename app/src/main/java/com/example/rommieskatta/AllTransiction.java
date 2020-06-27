package com.example.rommieskatta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllTransiction extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference("Expenses");
    ArrayList<ExpensesData> listData;
    LAdapter lAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transiction);
         recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllTransiction.this));
        listData=new ArrayList<ExpensesData>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listData.clear();
               // list = new ArrayList<ExpensesData>();
                for (DataSnapshot data:dataSnapshot.getChildren()) {

                    String name=data.child("name").getValue().toString();
                    String details=data.child("detail").getValue().toString();
                    String amount=data.child("amount").getValue().toString();
                    String date=data.child("date").getValue().toString();
                    ExpensesData expensesData=new ExpensesData(name,details,amount,date);
                    listData.add(expensesData);
                }
                lAdapter=new LAdapter(AllTransiction.this,listData);
                recyclerView.setAdapter(lAdapter);
                lAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                String error=databaseError.toString();
                Toast.makeText(AllTransiction.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                Log.i("DataError ",error);
            }
        });


    }
}