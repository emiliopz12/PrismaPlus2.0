package com.prismaplus.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.prismaplus.R;
import com.prismaplus.activities.ClientsActivity;
import com.prismaplus.entities.ClientInfo;
import com.prismaplus.entities.ProductInfo;
import com.prismaplus.herlpers.PreferencesManager;
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


public class ClientFragment extends Fragment {

    View rootView;
    private ClientsActivity mActivity;
    private PreferencesManager preferencesManager;
    private ConnectionInterface connetionService;
    Utils utils;
    private final int HOME = 16908332;

    @BindView(R.id.spinner_id)
    Spinner spinner_id;

    @BindView(R.id.spinner_state)
    Spinner spinner_state;



    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.id)
    EditText id;

    @BindView(R.id.namecomm)
    EditText namecomm;

    @BindView(R.id.email)
    EditText email;

    ClientInfo client;

    public ClientFragment() {
        // Required empty public constructor
        utils  = new Utils();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setHasOptionsMenu(true);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setTitle("CERAR CLIENTE");

        preferencesManager = PreferencesManager.getInstance();
        connetionService = ConnetionService.obtenerServicio(preferencesManager.getStringValue(getActivity(),"url").equals("pruebas") ? utils.URL_PRUEBAS : utils.URL_PROD);


        try {
            mActivity.getSupportActionBar().setIcon(R.drawable.ic_prisma_big);
        }catch (Exception e){
            mActivity.getSupportActionBar().setIcon(R.drawable.ic_prisma);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_client, container, false);
        ButterKnife.bind(this,rootView);

        ArrayAdapter<String> spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.idType));
        spinner_id.setAdapter(spinnerConditionrrayAdapter);

        spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.state));
        spinner_state.setAdapter(spinnerConditionrrayAdapter);


        if(this.getArguments() != null && !this.getArguments().isEmpty()) {
            client = this.getArguments().getParcelable("client");


            name.setText(client.getNombre());
            id.setText(client.getIdCliente());
            namecomm.setText(client.getNombreComercial());
            email.setText(client.getEmail());

            if (client.getEstado() == 1)
                spinner_state.setSelection(0);
            else
                spinner_state.setSelection(1);

            if (client.getTipoCedula() == "01")
                spinner_id.setSelection(0);
            else if (client.getTipoCedula() == "02")
                spinner_id.setSelection(1);
            else if (client.getTipoCedula() == "03")
                spinner_id.setSelection(2);
            else if (client.getTipoCedula() == "04")
                spinner_id.setSelection(3);

            Log.d("Client", client.getIdCliente());
        }
        else
            client = new ClientInfo();

        return  rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case HOME:
                mActivity.setFragment(new ClientListFragment(), 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.fabAdd)
    public void saveClient() {


        utils.showProgess(getActivity(),"Procesando");

        client.setIdCliente(id.getText().toString());
        client.setNombre(name.getText().toString());

        String typeId = spinner_id.getSelectedItem().toString();

        Log.d("TypeID: ", typeId);

        if(typeId.equals("Fisico"))
            client.setTipoCedula("01");
        else if(typeId.equals("Juridica"))
            client.setTipoCedula("02");
        else if(typeId.equals("Dimex"))
            client.setTipoCedula("03");
        else if(typeId.equals("Nise"))
            client.setTipoCedula("04");

        //client.setTipoCedula(name.getText().toString());
        client.setNombreComercial(namecomm.getText().toString());
        client.setEstado(spinner_state.getSelectedItemPosition() == 0 ? 1: 0);
        client.setEmail(email.getText().toString());
        String IdEmpresa = String.valueOf(preferencesManager.getIntValue(getActivity(),"IdEmpresa"));
        client.setIdEmpresa(IdEmpresa);

        if(client.getIdentificacion() == null)
            client.setIdentificacion("");
        if(client.getOtrasSenas() == null)
            client.setOtrasSenas("");

        connetionService.saveClient(client).enqueue(new Callback<ClientInfo>() {

            @Override
            public void onResponse(Call<ClientInfo> call, Response<ClientInfo> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();

                mActivity.setFragment(new ClientListFragment(), 1);

                utils.hideProgress();


            }

            @Override
            public void onFailure(Call<ClientInfo> call, Throwable t) {

                mActivity.setFragment(new ClientListFragment(), 1);

                utils.hideProgress();
            }
        });

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (ClientsActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
