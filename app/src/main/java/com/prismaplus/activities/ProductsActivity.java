package com.prismaplus.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.fragments.ClientListFragment;
import com.prismaplus.fragments.ProductsListFragment;

public class ProductsActivity extends AppCompatActivity {

    private int nextFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        nextFragment = getIntent().getIntExtra("nextFragment",1);
        openFragmentFromDrawer(nextFragment);
    }

    public void setFragment(Fragment fragment, int id) {
        nextFragment = id;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorFragmentProduct, fragment);
        fragmentTransaction.commit();
    }

    private void openFragmentFromDrawer(int id){

        nextFragment = id;
        switch (id){
            case 1 : setFragment(new ProductsListFragment(), 1); break;
            default: break;
        }
    }

    @Override
    public void onBackPressed() {

        if(nextFragment == 2){
            openFragmentFromDrawer(1);
        }
        else {
            Intent i = new Intent(ProductsActivity.this, DrawerActivity.class);
            startActivity(i);
            finish();
        }

    }
}
