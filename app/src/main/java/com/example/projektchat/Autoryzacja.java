package com.example.projektchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;

import com.example.projektchat.databinding.ActivityAutoryzacjaBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Autoryzacja extends AppCompatActivity {

    ActivityAutoryzacjaBinding binding;
    String nazwa, email, haslo;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAutoryzacjaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseReference= FirebaseDatabase.getInstance("https://projekt-chat-9295e-default-rtdb.europe-west1.firebasedatabase.app/").getReference("uzytkownicy");


        binding.zaloguj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email=binding.email.getText().toString();
                haslo=binding.haslo.getText().toString();
                
                logowanie();
            }
        });
        binding.zarejestruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nazwa=binding.nazwa.getText().toString();
                email=binding.email.getText().toString();
                haslo=binding.haslo.getText().toString();

                rejestracja();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(Autoryzacja.this, MainActivity.class));
            finish();

        }
    }

    private void logowanie() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.trim(),haslo)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                       startActivity(new Intent(Autoryzacja.this,MainActivity.class));
                       finish();
                    }
                });
    }
    private void rejestracja() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.trim(),haslo)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.
                                Builder().setDisplayName(nazwa).build();
                        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                        firebaseUser.updateProfile(userProfileChangeRequest);
                        Uzytkownik uzytkownik=new Uzytkownik(FirebaseAuth.getInstance().getUid(),nazwa,email,haslo);
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(uzytkownik);
                        startActivity(new Intent(Autoryzacja.this,MainActivity.class));
                        finish();
                    }
                });
    }
}