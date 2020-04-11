package com.example.narah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
//this is test2S
    private Button btg_onclick ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btg_onclick=findViewById(R.id.btg_onclick);
    }



    public void btg(View view) {
       // Intent intent= new Intent(this,class name);
      //    startActivity(intent);
    }
}
