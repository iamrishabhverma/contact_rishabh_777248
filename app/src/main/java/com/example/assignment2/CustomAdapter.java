package com.example.assignment2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    ArrayList<String> column_id, FirstName, LastName, PhoneNumber,Email,Address;

    CustomAdapter(Activity activity, Context context, ArrayList column_id, ArrayList FirstName, ArrayList LastName,
                  ArrayList PhoneNumber, ArrayList Email, ArrayList Address){
        this.activity = activity;
        this.context = context;
        this.column_id = column_id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Address = Address;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.column_id_txt.setText(String.valueOf(column_id.get(position)));
        holder.firstName_txt.setText(String.valueOf(FirstName.get(position)));
        holder.lastName_txt.setText(String.valueOf(LastName.get(position)));
        holder.phoneNumber_int.setText(String.valueOf(PhoneNumber.get(position)));
        holder.email_txt.setText(String.valueOf(Email.get(position)));
        holder.address_txt.setText(String.valueOf(Address.get(position)));

        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(column_id.get(position)));
                intent.putExtra("First Name", String.valueOf(FirstName.get(position)));
                intent.putExtra("Last Name", String.valueOf(LastName.get(position)));
                intent.putExtra("Phone Number", String.valueOf(PhoneNumber.get(position)));
                intent.putExtra("E-Mail", String.valueOf(Email.get(position)));
                intent.putExtra("Address", String.valueOf(Email.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });





    }

    @Override
    public int getItemCount() {
        return column_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView column_id_txt, firstName_txt, lastName_txt, phoneNumber_int,email_txt,address_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            column_id_txt = itemView.findViewById(R.id.column_id_txt);
            firstName_txt = itemView.findViewById(R.id.firstName_txt);
            lastName_txt = itemView.findViewById(R.id.lastName_txt);
            phoneNumber_int = itemView.findViewById(R.id.phoneNumber_int);
            email_txt = itemView.findViewById(R.id.email_txt);
            address_txt = itemView.findViewById(R.id.address_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
