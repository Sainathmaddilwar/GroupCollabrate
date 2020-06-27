package com.example.rommieskatta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainData extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    DatabaseReference ref;
   ArrayList<String> arrayList=new ArrayList<>();
  Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data);
        final TextView money=(TextView)findViewById(R.id.MainMoney);
        Button allTransiction=(Button)findViewById(R.id.MainAlltransiction);
        Button addExpenses=(Button)findViewById(R.id.MainAddexpenses);
        logout=(Button)findViewById(R.id.Logout);
        final ListView listView=(ListView)findViewById(R.id.List);
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference("Users");
        ref=database.getReference("Deposited");
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainData.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent=new Intent(MainData.this,Deposite.class);
               String name=arrayList.get(position);
               intent.putExtra("Name",name);
               startActivity(intent);
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    Users users=data.getValue(Users.class);
                    String name=users.getName();
                    arrayList.add(name);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int x=0;
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    //DepositeData depositeData=data.getValue(DepositeData.class);
                    String md=data.child("moneyDeposited").getValue().toString();
                    int i=Integer.parseInt(md);
                    x=x+i;
                }
                String ms=String.valueOf(x);
                money.setText("₹"+ms);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
         allTransiction.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(MainData.this,AllTransiction.class);
                 startActivity(intent);
             }
         });
        addExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainData.this,Expenses.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(MainData.this,MainActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish();
                //return;
            }
        });
    }
}