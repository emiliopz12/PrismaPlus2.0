package com.prismaplus.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
import com.prismaplus.herlpers.PreferencesManager;


public class LoginFragment extends Fragment {

    ConnectionInterface connetionService;
    PreferencesManager preferencesManager;
    LoginActivity mActivity;
    View rootView;
    Utils utils;
    @BindView(R.id.loginUser)
    Button loginUser;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.relaData)
    RelativeLayout relaData;

    @BindView(R.id.averiasImagen)
    ImageView averiasImagen;

    public LoginFragment() {
        // Required empty public constructor
        utils  = new Utils();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connetionService = ConnetionService.obtenerServicio();
        preferencesManager = PreferencesManager.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,rootView);
        if(utils.getHeightOfScreen(rootView.getContext()) <= 1080){

        }
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

    public void goToMain(){
        Intent homepage = new Intent(getActivity(), DrawerActivity.class);
        startActivity(homepage);
        getActivity().finish();
    }

    @OnClick(R.id.textNot)
    public void forgotPass(){
        mActivity.setFragment(new ForgotPassFragment());
    }

    @OnClick(R.id.loginUser)
    public void login(){
        //Toast.makeText(rootView.getContext(), username.getText().toString(), Toast.LENGTH_LONG).show();
        //Log.d("usuario", password.getText().toString());
        utils.showProgess(getActivity(),"Iniciando sesión");
        connetionService.doLogin(username.getText().toString(), password.getText().toString()).enqueue(new Callback<List<LoginInfo>>() {
            @Override
            public void onResponse(Call<List<LoginInfo>> call, Response<List<LoginInfo>> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();
                List<LoginInfo> loginResponse = response.body();
                //loginResponse.get(0).getMSJ();
                LoginInfo res = loginResponse.get(0);
                if(res.getMSJ().equals("OK")){
                    //Toast.makeText(rootView.getContext(), "Logged In", Toast.LENGTH_LONG).show();
                    preferencesManager.saveString(getActivity(), "rememberUser", "OK");
                    preferencesManager.saveInt(getActivity(), "IdEmpresa", res.getIdEmpresa());
                    preferencesManager.saveString(getActivity(), "nombre", res.getNombre());
                    preferencesManager.saveString(getActivity(), "username", username.getText().toString());
                    utils.hideProgress();
                    goToMain();
                }
                else{
                    utils.hideProgress();
                    Toast.makeText(rootView.getContext(), res.getMSJ(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<LoginInfo>> call, Throwable t) {
                utils.hideProgress();
                Toast.makeText(rootView.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

}
