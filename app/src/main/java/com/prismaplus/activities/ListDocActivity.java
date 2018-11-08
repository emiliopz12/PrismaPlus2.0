package com.prismaplus.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.fragments.ClientListFragment;
import com.prismaplus.fragments.ListDocsFragment;

public class ListDocActivity extends AppCompatActivity implements OnPageChangeListener {

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
        if(id == 4)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void openFragmentFrom(int id){

        nextFragment = id;
        switch (id){
            case 1 : setFragment(new ListDocsFragment(), 1); break;
            default: break;
        }
    }

    @Override
    public void onBackPressed() {

        if(nextFragment == 4){
            int count = getFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                super.onBackPressed();
                getFragmentManager().popBackStack();
                nextFragment = 1;
            } else {
                getFragmentManager().popBackStack();
                nextFragment = 1;

            }        }
        else {
            Intent i = new Intent(ListDocActivity.this, DrawerActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }
}
