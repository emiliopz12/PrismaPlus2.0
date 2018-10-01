package com.prismaplus.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.activities.LoginActivity;
import com.prismaplus.entities.LoginInfo;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.prismaplus.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgotPassFragment extends Fragment {

    @BindView(R.id.loginUser)
    Button loginUser;

    @BindView(R.id.username_recovery)
    EditText username;

    View rootView;
    ConnectionInterface connetionService;
    Utils utils;
    LoginActivity mActivity;



    public ForgotPassFragment() {
        // Required empty public constructor
        utils  = new Utils();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connetionService = ConnetionService.obtenerServicio();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_forgot_pass, container, false);
        ButterKnife.bind(this,rootView);

        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (LoginActivity) context ;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.loginUser)
    public void forgot(){
        //Toast.makeText(rootView.getContext(), username.getText().toString(), Toast.LENGTH_LONG).show();
        //Log.d("usuario", password.getText().toString());
        utils.showProgess(getActivity(),"Procesando");
        connetionService.forgotPass(username.getText().toString()).enqueue(new Callback<List<LoginInfo>>() {
            @Override
            public void onResponse(Call<List<LoginInfo>> call, Response<List<LoginInfo>> response) {

                utils.hideProgress();
                new MaterialDialog.Builder(rootView.getContext())
                        .title("Mensaje")
                        .content("Se ha enviado el código al correo "+username.getText().toString()+" satisfactoriamente")
                        .contentGravity(GravityEnum.CENTER)
                        .positiveText("Aceptar")
                        .onPositive((dialog, which) -> {
                            mActivity.setFragment(new LoginFragment());
                        })
                        .show();
            }

            @Override
            public void onFailure(Call<List<LoginInfo>> call, Throwable t) {

                utils.hideProgress();
                Toast.makeText(rootView.getContext(), "Ocurrió un error, intentalo de nuevo", Toast.LENGTH_LONG).show();

            }
        });
    }


}
