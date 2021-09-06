package com.cust.alumini.retrofitpost2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextInputEditText username, firstname, lastname, email;
    Button btn;
    String name, lname, fname, uemail;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        firstname= findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email= findViewById(R.id.email);
        btn = findViewById(R.id.btnregister);
        text = findViewById(R.id.text);

    }

    public void PostData(View view) {
        name = username.getText().toString();
        lname = lastname.getText().toString();
        fname = firstname.getText().toString();
        uemail = email.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://httpbin.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OurRetrofit ourRetrofit = retrofit.create(OurRetrofit.class);
        OurDataSet ourDataSet = new OurDataSet(name, fname, lname, uemail);
        Call<OurDataSet> call= ourRetrofit.PostData(ourDataSet);

        call.enqueue(new Callback<OurDataSet>() {
            @Override
            public void onResponse(Call<OurDataSet> call, Response<OurDataSet> response) {
                text.setText(response.body().getJson().getUsername()+ " "+response.body().getJson().getEmail());
            }

            @Override
            public void onFailure(Call<OurDataSet> call, Throwable t) {
                text.setText("Connection Fail");
            }
        });
    }
}