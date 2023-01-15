package com.example.projektchat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MsgAdapter extends  RecyclerView.Adapter<MsgAdapter.MyViewHolder>{

    private Context context;

    public List<Wiadomosci> getWiadomosciList() {
        return wiadomosciList;
    }

    private List<Wiadomosci> wiadomosciList;

    public MsgAdapter(Context context) {
        this.context = context;
        wiadomosciList=new ArrayList<>();
    }

    public void addWiadomosciCollection(List<Wiadomosci> lista){
        wiadomosciList=lista;
        notifyDataSetChanged();
    }

    public void dodaj(Wiadomosci wiadomosci){
        wiadomosciList.add(wiadomosci);
        notifyDataSetChanged();

        Log.d("iop",wiadomosciList.toString());

    }
    public void clear(){
        wiadomosciList.clear();
        notifyDataSetChanged();
        Collections.sort(wiadomosciList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view    = LayoutInflater.from(parent.getContext()).inflate(R.layout.czat,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        Wiadomosci wiadomosci=wiadomosciList.get(position);
        Collections.sort(wiadomosciList);
        holder.msg.setText(wiadomosci.getWiadomosc());
        if(wiadomosci.getWyslaneId().equals(FirebaseAuth.getInstance().getUid())){
            holder.ll.setBackground(context.getDrawable(R.drawable.rounded_cornerwyslane));
            holder.msg.setTextColor(context.getResources().getColor(R.color.white));
        }
        else{
           holder.ll.setBackground(context.getDrawable(R.drawable.rounded_cornerodebrane));
           holder.msg.setTextColor(context.getResources().getColor(R.color.white));
        }



    }

    @Override
    public int getItemCount() {
        return wiadomosciList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView msg;
        private LinearLayout ll;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            msg=itemView.findViewById(R.id.wiadomosc);
            ll=itemView.findViewById(R.id.Mainwiadomosci);
        }
    }

}
