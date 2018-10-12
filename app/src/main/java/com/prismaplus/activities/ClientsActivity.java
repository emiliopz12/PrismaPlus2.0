package com.prismaplus.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.fragments.ClientListFragment;
import com.prismaplus.fragments.NewBillFragment;

public class ClientsActivity extends AppCompatActivity {

    int nextFragment = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);
        nextFragment = getIntent().getIntExtra("nextFragment",0);
        openFragmentFromBilling(nextFragment);
        //Objects.requireNonNull(getSupportActionBar()).setLogo(R.drawable.ic_prisma_big);
    }

    public void setFragment(Fragment fragment, int id) {
        nextFragment = id;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorFragmentClient, fragment);
        fragmentTransaction.commit();
    }

    private void openFragmentFromBilling(int id){

        nextFragment = id;
        switch (id){
            case 1 : setFragment(new ClientListFragment(), 1); break;
            default: break;
        }
    }

    @Override
    public void onBackPressed() {

            if(nextFragment == 2){
                openFragmentFromBilling(1);
            }
            else {
                Intent i = new Intent(ClientsActivity.this, DrawerActivity.class);
                startActivity(i);
                finish();
            }

    }
}
