package com.example.projektchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.projektchat.databinding.ActivityCzatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Czat extends AppCompatActivity {
    ActivityCzatBinding binding;
    String idUzytkownik;
    DatabaseReference databaseReferenceOdebrane,databaseReferenceWyslane;
    String wyslane,odebrane;
    MsgAdapter msgAdapter;
    String timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCzatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        idUzytkownik =getIntent().getStringExtra("id");


        wyslane= FirebaseAuth.getInstance().getUid()+ idUzytkownik;
        odebrane= idUzytkownik +FirebaseAuth.getInstance().getUid();
        msgAdapter=new MsgAdapter(this);
        binding.recycler.setAdapter(msgAdapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));

        databaseReferenceWyslane= FirebaseDatabase.getInstance("https://projekt-chat-9295e-default-rtdb.europe-west1.firebasedatabase.app/").getReference("czaty").child(wyslane);
        databaseReferenceOdebrane= FirebaseDatabase.getInstance("https://projekt-chat-9295e-default-rtdb.europe-west1.firebasedatabase.app/").getReference("czaty").child(odebrane);
        databaseReferenceWyslane.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                msgAdapter.clear();

                List<Wiadomosci> tempwiadomoscilist=new ArrayList<>();

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Wiadomosci wiadomosci=dataSnapshot.getValue(Wiadomosci.class);
                    tempwiadomoscilist.add(wiadomosci);
                   //msgAdapter.dodaj(wiadomosci);
                }
                Collections.sort(tempwiadomoscilist);
                msgAdapter.addWiadomosciCollection(tempwiadomoscilist);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.wysylaniewiad.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                timeStamp = new SimpleDateFormat("dd-MM-yy HH:mm a").format(Calendar.getInstance().getTime());
                String wiadomosc=binding.polewiadomosc.getText().toString();
                if(wiadomosc.trim().length()>0){
                    wyslijWiadomosc(wiadomosc);
                }
                for(int i=0; i<msgAdapter.getWiadomosciList().size();i++){
                    Log.d("ZXC",msgAdapter.getWiadomosciList().get(i).getWiadomosc());
                }
            }
        });

    }

    private void wyslijWiadomosc(String wiadomosc) {
        String msgid= UUID.randomUUID().toString();
        Wiadomosci wiadomosci=new Wiadomosci(msgid,FirebaseAuth.getInstance().getUid(),wiadomosc,timeStamp);

        msgAdapter.dodaj(wiadomosci);

        databaseReferenceWyslane.child(msgid).setValue(wiadomosci);
        databaseReferenceOdebrane.child(msgid).setValue(wiadomosci);
    }
}