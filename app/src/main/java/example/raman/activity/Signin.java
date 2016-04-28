package example.raman.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.raman.R;
import example.raman.database.AuthDatabase;
import example.raman.database.AuthDto;

public class Signin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        final EditText firstname= (EditText) findViewById(R.id.firstnameEditText);
        final EditText lastname= (EditText) findViewById(R.id.lastnameEditText);
        final EditText email= (EditText) findViewById(R.id.emailEditText);


        final Button signInButton=(Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(firstname.getText()!=null&&lastname.getText()!=null&&email.getText()!=null)
                {
                    AuthDatabase authDatabase=new AuthDatabase(Signin.this);
                    int key= authDatabase.addInfo(new AuthDto(firstname.getText().toString(), lastname.getText().toString(), email.getText().toString()));

                  saveLoginCredentials(key);

                  Intent intent = new Intent(Signin.this, Profile.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("id",fetchLoginCredentials());

                    startActivity(intent);
                }
            }
        });

    }

    private void saveLoginCredentials(int id) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("IDstorage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", id);
        editor.commit();
    }
    private int fetchLoginCredentials() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("IDstorage", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id",0);
    }
}
