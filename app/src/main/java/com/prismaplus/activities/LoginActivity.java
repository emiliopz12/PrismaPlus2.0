package com.prismaplus.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.fragments.LoginFragment;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    private int nextFragment = 0;


    public  LoginActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        setFragment(new LoginFragment(), 1);
        //connetionService = ConnetionService.obtenerServicio();
    }


    public void setFragment(Fragment fragment, int i) {
        nextFragment = i;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        if(nextFragment != 1){
            setFragment( new LoginFragment(), 1);
        }
else {
            super.onBackPressed();
        }
    }


}
