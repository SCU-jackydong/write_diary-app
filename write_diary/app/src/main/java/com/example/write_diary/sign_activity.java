package com.example.write_diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class sign_activity extends AppCompatActivity {
private  EditText editText_name;
    private  EditText editText_age;
    private  EditText editText_account;
    private  EditText editText_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        editText_name =(EditText) findViewById(R.id.edit1);
         editText_age =(EditText) findViewById(R.id.edit2);
       editText_account =(EditText) findViewById(R.id.edit3);
     editText_password =(EditText) findViewById(R.id.edit4);

        Button button1 =(Button) findViewById(R.id.zhuce_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithOkhttp();

                //startActivity(intent);

            }
        });
    }

    public void sendRequestWithOkhttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String name = editText_name.getText().toString();
                    String age = editText_age.getText().toString();
                    String account = editText_account.getText().toString();
                    String password = editText_password.getText().toString();

                    RequestBody requestBody = new FormBody.Builder()
                            .add("name",name)
                            .add("age",age)
                            .add("account",account)
                            .add("password",password)
                            .build();

                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.0.222:8080/zhuce")
                            .post(requestBody)
                            .build();

                    Response response =okHttpClient.newCall(request).execute();
                    String response_data = response.body().string();
                    System.out.println("response_data");
                    System.out.println(response_data);


                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}