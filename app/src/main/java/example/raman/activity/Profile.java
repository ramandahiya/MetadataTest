package example.raman.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import example.raman.R;
import example.raman.database.AuthDatabase;
import example.raman.database.AuthDto;
import example.raman.database.DetailDatabase;
import example.raman.database.DetailDto;
import example.raman.database.Employee;

public class Profile extends AppCompatActivity {
    EditText emailEditText;
    EditText addressEditText;
    EditText phoneEditText;
    EditText designationEditText;
    EditText departmentEditText;
    EditText incomeEditText;
    AuthDatabase  authDatabase;
    DetailDatabase detailDatabase;
    Cursor authCursor;
    Cursor detailCursor;
    int id;
    boolean flagForDeatilTable=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        disablingEditTextViews();
        id=getIntent().getExtras().getInt("id",0);
        fetchInfo();
    }

    private void fetchInfo() {

        authDatabase=new AuthDatabase(this);
        detailDatabase=new DetailDatabase(this);

        authCursor= authDatabase.getInfo(id);
        detailCursor=  detailDatabase.getInfo(id);

        try {
           ArrayList<Employee> employees = authDatabase.getEmployees();
       }catch (Exception j){}


         CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
         toolBarLayout.setTitle(authCursor.getString(1) + " " +authCursor.getString(2));


         emailEditText.setText(authCursor.getString(3));

        if(detailCursor.getCount()!=0) {
          incomeEditText.setText(detailCursor.getString(5));
          phoneEditText.setText(detailCursor.getString(2));
          designationEditText.setText(detailCursor.getString(3));
          departmentEditText.setText(detailCursor.getString(4));
          addressEditText.setText(detailCursor.getString(1));
        }
    }

    private void updateInfo() {

        AuthDto authDto=new AuthDto(id,authCursor.getString(1),authCursor.getString(2),emailEditText.getText().toString());
        authDatabase.updateInfo(authDto);

       if( detailCursor.getCount()!=0 ||!flagForDeatilTable) {
           DetailDto detailDto = new DetailDto(id, incomeEditText.getText().toString(), phoneEditText.getText().toString(), designationEditText.getText().toString(), departmentEditText.getText().toString(), addressEditText.getText().toString());
           detailDatabase.updateInfo(detailDto);

       }else{
           flagForDeatilTable=false;
           DetailDto detailDto = new DetailDto(id, incomeEditText.getText().toString(), phoneEditText.getText().toString(), designationEditText.getText().toString(), departmentEditText.getText().toString(), addressEditText.getText().toString());
           detailDatabase.addInfo(detailDto);


       }
    }

    private void disablingEditTextViews() {
         emailEditText= (EditText) findViewById(R.id.emailEditText);
         addressEditText= (EditText) findViewById(R.id.addressEditText);
         phoneEditText= (EditText) findViewById(R.id.phoneEditText);
         designationEditText= (EditText) findViewById(R.id.designationEditText);
         departmentEditText= (EditText) findViewById(R.id.departmentEditText);
         incomeEditText = (EditText) findViewById(R.id.incomeEditText);

        emailEditText.setEnabled(false);
        addressEditText.setEnabled(false);
        phoneEditText.setEnabled(false);
        designationEditText.setEnabled(false);
        departmentEditText.setEnabled(false);
        incomeEditText.setEnabled(false);

        final  FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (emailEditText.isEnabled()) {
                    emailEditText.setEnabled(false);
                    addressEditText.setEnabled(false);
                    phoneEditText.setEnabled(false);
                    designationEditText.setEnabled(false);
                    departmentEditText.setEnabled(false);
                    incomeEditText.setEnabled(false);

                    emailEditText.setBackgroundResource(android.R.color.transparent);
                    addressEditText.setBackgroundResource(android.R.color.transparent);
                    phoneEditText.setBackgroundResource(android.R.color.transparent);
                    designationEditText.setBackgroundResource(android.R.color.transparent);
                    departmentEditText.setBackgroundResource(android.R.color.transparent);
                    incomeEditText.setBackgroundResource(android.R.color.transparent);


                    fab.setImageResource(android.R.drawable.ic_menu_revert);

                    updateInfo();

                } else
                {
                    emailEditText.setEnabled(true);
                    addressEditText.setEnabled(true);
                    phoneEditText.setEnabled(true);
                    designationEditText.setEnabled(true);
                    departmentEditText.setEnabled(true);
                    incomeEditText.setEnabled(true);

                    emailEditText.setBackgroundResource(R.drawable.edittext_bgd);
                    addressEditText.setBackgroundResource(R.drawable.edittext_bgd);
                    phoneEditText.setBackgroundResource(R.drawable.edittext_bgd);
                    designationEditText.setBackgroundResource(R.drawable.edittext_bgd);
                    departmentEditText.setBackgroundResource(R.drawable.edittext_bgd);
                    incomeEditText.setBackgroundResource(R.drawable.edittext_bgd);

                    fab.setImageResource(android.R.drawable.ic_menu_edit);

                }
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        detailCursor.close();authCursor.close();
    }
}
