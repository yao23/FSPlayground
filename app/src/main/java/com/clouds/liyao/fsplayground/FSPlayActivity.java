package com.clouds.liyao.fsplayground;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.clouds.liyao.fsplayground.API.GitAPI;
import com.clouds.liyao.fsplayground.Model.GitModel;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FSPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsplay);
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

        String user = "yao23";
        String baseURL = "https://api.github.com";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitAPI git = retrofit.create(GitAPI.class);
        Call call = git.getUser(user);
        call.enqueue(new Callback<GitModel>() {
            @Override
            public void onResponse(Response<GitModel> response) {
                GitModel model = response.body();

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
                    //200
                    System.out.println("Github Name :" + model.getName() + "\nWebsite :" + model.getBlog() + "\nCompany Name :" + model.getCompany());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("t = " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fsplay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the SignIn button */
    public void signIn(View view) {
        // Do something in response to button
        System.out.println("press sign in button");

        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks the SignUp button */
    public void signUp(View view) {
        // Do something in response to button
        System.out.println("press sign up button");
    }

    /** Called when the user clicks the FB SignIn button */
    public void FBSignIn(View view) {
        // Do something in response to button
        System.out.println("press FB sign in button");
    }
}
