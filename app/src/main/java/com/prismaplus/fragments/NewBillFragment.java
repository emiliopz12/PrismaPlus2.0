package com.prismaplus.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.afollestad.materialdialogs.MaterialDialog;
import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.activities.BillingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NewBillFragment extends Fragment {

    View rootView;
    private final int HOME = 16908332;
    BillingActivity mActivity;



    public NewBillFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        /*mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setTitle("Nueva Factura");
        mActivity.getSupportActionBar().setIcon(R.drawable.ic_prisma_big);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_new_bill, container, false);
        ButterKnife.bind(this,rootView);
        return  rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //mActivity = (BillingActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Log.d("id", String.valueOf(item.getItemId()));
        switch (item.getItemId()) {
            case HOME:
                Intent homepage = new Intent(mActivity, DrawerActivity.class);
                startActivity(homepage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


}
