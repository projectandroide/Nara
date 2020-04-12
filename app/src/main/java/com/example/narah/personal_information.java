package com.example.narah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.narah.personal.Personal;
import com.google.gson.Gson;

public class personal_information extends AppCompatActivity {


    EditText ID;
    EditText Name;
    EditText Address;
    RadioButton Cash;
    RadioButton CRCard;
   TextView TVCard;
    EditText CDID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        Name = findViewById(R.id.editText);
        Address = findViewById(R.id.editText2);
        ID = findViewById(R.id.editText3);
        Cash =findViewById(R.id.radioButton);
        CRCard =findViewById(R.id.radioButton2);
        CDID = findViewById(R.id.editText4);
        TVCard = findViewById(R.id.textView5);
        if(Cash.isChecked()){
            CDID.setVisibility(View.INVISIBLE);
            TVCard.setVisibility(View.INVISIBLE);
        }
        final RadioGroup radio = (RadioGroup) findViewById(R.id.RG);
        radio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);
                switch (index) {
                    case 0: // first button

                        CDID.setVisibility(View.INVISIBLE);
                        TVCard.setVisibility(View.INVISIBLE);
                        break;
                    case 1: // secondbutton
                        CDID.setVisibility(View.VISIBLE);
                        TVCard.setVisibility(View.VISIBLE);
                        break;
                }
            }  });
    }

static int idCount=0;
    public void btnOnclick(View view) {
        String cash,Credit;

        if ( !Cash.isChecked() && CDID.getText().toString().length()==0){
            Toast.makeText(this,"You need to put money in CD text",Toast.LENGTH_LONG).show();

        }
else{
            SharedPreferences shardpre = getSharedPreferences("personal_data",MODE_PRIVATE);
            SharedPreferences.Editor editor= shardpre.edit();
            if(Cash.isChecked()) {
               cash="cash";
               Credit="";
            }else{
                cash="Not Cash";
                Credit=CDID.getText().toString();


            }
            Personal pers = new Personal(Name.getText().toString(),Address.getText().toString(), ID.getText().toString(),Credit,cash);
//            editor.putString("name",Name.getText().toString());
//            editor.putString("address",Address.getText().toString());
//            editor.putString("ID",ID.getText().toString());
            Gson gson = new Gson();

            editor.putString(""+(++idCount),gson.toJson(pers));
            editor.commit();

            SharedPreferences sh =getSharedPreferences(getString(R.string.personal_data),MODE_PRIVATE);
//            String data = sh.getString("name","");
//            data+="   Address "+sh.getString("address","")+ "  ID "+sh.getString("ID","");
            Gson g = new Gson();
            String per= sh.getString("1","");
            Personal p = g.fromJson(per,Personal.class);
            Toast.makeText(this,p.getName()+"Done",Toast.LENGTH_LONG).show();

        }
    }
}
