package com.example.rommieskatta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Deposite extends AppCompatActivity {
FirebaseDatabase database;
DatabaseReference databaseReference;
    DepositeData depositeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposite);
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("Deposited");
        String s=getIntent().getStringExtra("Name");
        DatabaseReference ref=databaseReference.child(s);

        final TextView test=(TextView)findViewById(R.id.named);
        final TextView Md=(TextView)findViewById(R.id.moneyd);
        final TextView re=(TextView)findViewById(R.id.moneyR);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String n=dataSnapshot.child("name").getValue().toString();
                    String md=dataSnapshot.child("moneyDeposited").getValue().toString();
                    String r=dataSnapshot.child("remaining").getValue().toString();
                    Md.setText(md);
                    re.setText(r);
                    test.setText(n);
                   depositeData=new DepositeData(n,md,r);

               // Md.setText(depositeData.getMoneyDeposited());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Deposite.this,"NOTHING TO SHOW",Toast.LENGTH_SHORT).show();
            }
        });
    }
}