package com.prismaplus.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.prismaplus.R;
import com.prismaplus.fragments.ClientListFragment;
import com.prismaplus.fragments.ListDocsFragment;

public class ListDocActivity extends AppCompatActivity {

    int nextFragment = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doc);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setTitle("LISTADO DE DOCUMENTOS");
        nextFragment = getIntent().getIntExtra("nextFragment",0);
        openFragmentFrom(nextFragment);
        //Objects.requireNonNull(getSupportActionBar()).setLogo(R.drawable.ic_prisma_big);
    }

    public void setFragment(Fragment fragment, int id) {
        nextFragment = id;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorFragmentListaProductosA, fragment);
        fragmentTransaction.commit();
    }

    private void openFragmentFrom(int id){

        nextFragment = id;
        switch (id){
            case 1 : setFragment(new ListDocsFragment(), 1); break;
            default: break;
        }
    }

}
