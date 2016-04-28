package example.raman.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import example.raman.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       int id= fetchLoginCredentials();
        if(id!=-1)
       {
           Intent intent = new Intent(Login.this, Profile.class);
           intent.putExtra("id",id);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
           startActivity(intent);
       }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button signInButton=(Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signin.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.LogInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "try again", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private int fetchLoginCredentials() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("IDstorage", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id",-1);
    }
}
