package com.prismaplus.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.prismaplus.R;
import com.prismaplus.activities.BillingActivity;
import com.prismaplus.activities.ClientsActivity;
import com.prismaplus.activities.ListDocActivity;
import com.prismaplus.activities.ProductsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class MainFragment extends Fragment {

    View rootView;

    @BindView(R.id.relative_fact)
    RelativeLayout relative_fact;

    @BindView(R.id.relative_clients)
    RelativeLayout relative_clients;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @OnClick(R.id.relative_fact)
    public void goTo(){
        Intent i = new Intent(getActivity(), BillingActivity.class);
        i.putExtra("nextFragment",1);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.relative_clients)
    public void goToclients(){
        Intent i = new Intent(getActivity(), ClientsActivity.class);
        i.putExtra("nextFragment",1);
        startActivity(i);
        getActivity().finish();
    }

    @OnClick(R.id.relative_prod)
    public void goToProducts(){
        Intent i = new Intent(getActivity(), ProductsActivity.class);
        i.putExtra("nextFragment",1);
        startActivity(i);
        getActivity().finish();
    }


    @OnClick(R.id.relative_prod)
    public void goToListado(){
        Intent i = new Intent(getActivity(),ListDocActivity.class);
        i.putExtra("nextFragment",1);
        startActivity(i);
        getActivity().finish();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this,rootView);
        return rootView;
    }

}
