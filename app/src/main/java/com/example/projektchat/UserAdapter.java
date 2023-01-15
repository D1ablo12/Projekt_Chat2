package com.example.projektchat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.MyViewHolder>{

    private Context context;
    private List<Uzytkownik> uzytkownikList;

    public UserAdapter(Context context) {
        this.context = context;
        uzytkownikList=new ArrayList<>();
    }
    public void dodaj(Uzytkownik uzytkownik){
        uzytkownikList.add(uzytkownik);
        notifyDataSetChanged();
    }
    public void clear(){
        uzytkownikList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view    = LayoutInflater.from(parent.getContext()).inflate(R.layout.uzytkownik_widok,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Uzytkownik uzytkownik=uzytkownikList.get(position);
        holder.nazwa.setText(uzytkownik.getuzytkownikNazwa());
        holder.email.setText(uzytkownik.getuzytkownikEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Czat.class);
                intent.putExtra("id",uzytkownik.getUzytkownikId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return uzytkownikList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView nazwa, email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nazwa=itemView.findViewById(R.id.nazwa_uzytkownika);
            email=itemView.findViewById(R.id.email_uzytkownika);
        }
    }
}
