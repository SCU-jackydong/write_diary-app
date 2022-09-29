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

public class login_activity extends AppCompatActivity {
private EditText editText_account;
    private EditText editText_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_account=(EditText)findViewById(R.id.edit1);
        editText_password=(EditText)findViewById(R.id.edit2);

        Button button1 =(Button) findViewById(R.id.zhuce_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(login_activity.this,sign_activity.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.login_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String account = editText_account.getText().toString();
                    String password = editText_password.getText().toString();

                    RequestBody requestBody = new FormBody.Builder()

                            .add("account",account)
                            .add("password",password)
                            .build();

                    OkHttpClient okHttpClient=new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.0.222:8080/zhuce.html")
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
        });
    }
}