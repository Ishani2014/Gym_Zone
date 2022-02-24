package com.example.myapplication3345;
import  android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Comman_Method {

    public Comman_Method(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
    public Comman_Method(View view,String message){
        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
    }
    public Comman_Method( Context context , Class<?> nextclass){
        Intent intent = new Intent(context,nextclass);
        context.startActivity(intent);
    }
}
