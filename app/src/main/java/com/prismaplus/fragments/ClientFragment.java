package com.prismaplus.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
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
    private OnFragmentInteractionListener mListener;

    @BindView(R.id.spinner_id)
    Spinner spinner_id;

    @BindView(R.id.spinner_state)
    Spinner spinner_state;

    @BindView(R.id.update)
    Button update;

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
                spinner_state.setSelection(1);
            else
                spinner_state.setSelection(0);

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

    @OnClick(R.id.update)
    public void saveClient() {


        utils.showProgess(getActivity(),"Procesando");

        client.setIdCliente(id.getText().toString());
        client.setNombre(name.getText().toString());

        String typeId = spinner_id.getSelectedItem().toString();

        if(typeId == "Fisico")
            client.setTipoCedula("01");
        else if(typeId == "Juridica")
            client.setTipoCedula("02");
        else if(typeId == "Dimex")
            client.setTipoCedula("03");
        else if(typeId == "Nise")
            client.setTipoCedula("04");

        //client.setTipoCedula(name.getText().toString());
        client.setNombreComercial(namecomm.getText().toString());
        client.setEstado(spinner_state.getSelectedItemPosition());
        client.setEmail(email.getText().toString());


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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (ClientsActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
