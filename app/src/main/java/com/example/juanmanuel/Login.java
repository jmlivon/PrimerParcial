package com.example.juanmanuel;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText user;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Button b = (Button) findViewById(R.id.btnIngresar);
        b.setOnClickListener(this);

        this.user = (EditText) findViewById(R.id.etUser);
        this.pass = (EditText) findViewById(R.id.etPass);
    }

    @Override
    public void onClick(View v) {
        if(R.id.btnIngresar == v.getId()){
            if (this.user != null && this.pass != null ){
                String strUser = this.user.getText().toString();
                String strPass = this.pass.getText().toString();
                String url = "http://192.168.2.180:3000/login/" + strUser + "/" + strPass;

                String response;
                HttpConnection myConn = new HttpConnection(url);
                try {
                    response = myConn.getStrDataByGET();
                    Log.d("LOG JSON",response);
                    if (XmlParser.validateLogin(response)){
                        Intent i = new Intent(this,MainActivity.class);
                        if (XmlParser.IsAdmin){
                            i.putExtra("IsAdmin",true);
                        }else{
                            i.putExtra("IsAdmin",false);
                        }
                        this.startActivity(i);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                Log.d("Error Login","User y/o Pass null");
            }
        }
    }
}
