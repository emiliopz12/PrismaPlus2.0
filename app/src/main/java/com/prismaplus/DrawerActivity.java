package com.prismaplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.prismaplus.activities.BillingActivity;
import com.prismaplus.activities.ClientsActivity;
import com.prismaplus.activities.ListDocActivity;
import com.prismaplus.activities.LoginActivity;
import com.prismaplus.activities.ProductsActivity;
import com.prismaplus.fragments.MainFragment;
import com.prismaplus.fragments.NewBillFragment;
import com.prismaplus.herlpers.PreferencesManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private PreferencesManager preferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setFragment(new MainFragment());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        preferencesManager = PreferencesManager.getInstance();

        View header = navigationView.getHeaderView(0);
        TextView text = (TextView) header.findViewById(R.id.nameCompany);

        if(!preferencesManager.getStringValue(DrawerActivity.this, "nombre").equals("")){
            text.setText(preferencesManager.getStringValue(DrawerActivity.this,"nombre"));
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_create_fact) {
            Intent i = new Intent(DrawerActivity.this, BillingActivity.class);
            i.putExtra("nextFragment",1);
            startActivity(i);
            finish();
        } else if(id == R.id.nav_clientes){
            Intent i = new Intent(DrawerActivity.this, ClientsActivity.class);
            i.putExtra("nextFragment",1);
            startActivity(i);
            finish();
        }else if(id == R.id.nav_docs){
            Intent i = new Intent(DrawerActivity.this,ListDocActivity.class);
            i.putExtra("nextFragment",1);
            startActivity(i);
            finish();
        }
        else if(id == R.id.nav_productos){
            Intent i = new Intent(DrawerActivity.this, ProductsActivity.class);
            i.putExtra("nextFragment",1);
            startActivity(i);
            finish();
        }

        /*else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        if (id == R.id.salir) {
            preferencesManager.saveString(this, "rememberUser", ""); //log out
            Intent loginpage = new Intent(this, LoginActivity.class);
            startActivity(loginpage);
            finish();
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorFragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }
}
