package com.example.myapplication3345;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeVH extends RecyclerView.ViewHolder
{
    public TextView txt_name,txt_position,txt_option,txt_email,txt_address;
    public EmployeeVH(@NonNull View itemView)
    {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_position = itemView.findViewById(R.id.txt_position);
        txt_option = itemView.findViewById(R.id.txt_option);
        txt_email = itemView.findViewById(R.id.txt_email);
        txt_address = itemView.findViewById(R.id.txt_address);
    }
}