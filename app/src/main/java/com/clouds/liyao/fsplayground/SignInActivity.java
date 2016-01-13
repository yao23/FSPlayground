package com.clouds.liyao.fsplayground;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.clouds.liyao.fsplayground.API.FSAPI;
import com.clouds.liyao.fsplayground.Model.LoginResponse;
import com.clouds.liyao.fsplayground.Model.Results;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String API_URL = "";
        String API_KEY = "";
        String email = "";
        String password = "";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FSAPI apigent = retrofit.create(FSAPI.class);
        Call call = apigent.login(email, password, API_KEY);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response) {
                LoginResponse model = response.body();

                if (model == null) {
                    //404 or the response cannot be converted to GitModel.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        try {
                            System.out.println("responseBody = " + responseBody.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("responseBody = null");
                    }
                } else {
                    if (model.getError()) {
                        System.out.println(model.getErrorMsg());
                    } else {
                        System.out.println("count num: " + model.getCount());
                        System.out.println(model.getErrorMsg()+", "+model.getErrorCode());
                        if (model.getResults() != null) {
                            Results user = model.getResults();
                            System.out.println(user.getUserId()+", "+user.getUserName()+", "+user.getUserIcon());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("t = " + t.getMessage());
            }
        });
    }

    /** Called when the user clicks the SignIn button */
    public void signIn(View view) {
        // Do something in response to button
        System.out.println("press sign in button");
    }
}
