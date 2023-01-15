package com.example.projektchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.projektchat.databinding.ActivityMainBinding;
import com.google.android.material.internal.ManufacturerUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseReference databaseReference;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userAdapter=new UserAdapter(this);
        binding.recycler.setAdapter(userAdapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        databaseReference= FirebaseDatabase.getInstance("https://projekt-chat-9295e-default-rtdb.europe-west1.firebasedatabase.app/").getReference("uzytkownicy");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userAdapter.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String uid=dataSnapshot.getKey();
                    if (!uid.equals(FirebaseAuth.getInstance().getUid())){
                        Uzytkownik uzytkownik=dataSnapshot.getValue(Uzytkownik.class);
                        userAdapter.dodaj(uzytkownik);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.wyloguj){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this,Autoryzacja.class));
            finish();
            return true;
        }
        return false;
    }
}