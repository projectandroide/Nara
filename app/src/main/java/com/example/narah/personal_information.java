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


    public void btnOnclick(View view) {
        String money;

        if ( !Cash.isChecked() && CDID.getText().toString().length()==0){
            Toast.makeText(this,"You need to put money in CD text",Toast.LENGTH_LONG).show();

        }
else{
            SharedPreferences shardpre = getSharedPreferences("personal_data",MODE_PRIVATE);
            SharedPreferences.Editor editor= shardpre.edit();
            editor.putString("name",Name.getText().toString());
            editor.putString("address",Address.getText().toString());
            editor.putString("ID",ID.getText().toString());
            if(Cash.isChecked()) {
                editor.putString("Pay", "Cash");
                editor.putString("CreditCard","");
            }else{
                editor.putString("Pay", "Not Cash");
                editor.putString("CreditCard",CDID.getText().toString());

            }
            editor.commit();

            SharedPreferences sh =getSharedPreferences(getString(R.string.personal_data),MODE_PRIVATE);
            String data = sh.getString("name","");
            data+="   Address "+sh.getString("address","")+ "  ID "+sh.getString("ID","");

            Toast.makeText(this,data+"Done",Toast.LENGTH_LONG).show();

        }
    }
}
