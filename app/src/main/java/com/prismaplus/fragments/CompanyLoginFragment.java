package com.prismaplus.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.activities.ClientsActivity;
import com.prismaplus.activities.LoginActivity;
import com.prismaplus.entities.ClientInfo;
import com.prismaplus.entities.CompanyLoginInfo;
import com.prismaplus.entities.ListDocsInfo;
import com.prismaplus.herlpers.PreferencesManager;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.prismaplus.utils.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompanyLoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    View rootView;
    private final int HOME = 16908332;
    private LoginActivity mActivity;

    private PreferencesManager preferencesManager;
    private ConnectionInterface connetionService;
    Utils utils;

    Map<Integer, String> clientsHash = new HashMap<>();;

    List<CompanyLoginInfo> clientsList = new ArrayList<>();
    List<TableRow> rows = new ArrayList<TableRow>();


    @BindView(R.id.tableCompanies)
    TableLayout tableClients;


    public CompanyLoginFragment() {
        // Required empty public constructor
        utils  = new Utils();

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setTitle("EMPRESAS");

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
        rootView = inflater.inflate(R.layout.fragment_company_login, container, false);
        ButterKnife.bind(this,rootView);
        pupulateClients();
        preferencesManager.saveString(getActivity(), "rememberUser", "");

        return  rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case HOME:
                Intent i = new Intent(getActivity(), DrawerActivity.class);
                i.putExtra("nextFragment",1);
                startActivity(i);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (LoginActivity) getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



    public void pupulateClients() {

        String username = preferencesManager.getStringValue(mActivity, "username");

        utils.showProgess(getActivity(),"Cargando");

        connetionService.getCompaniesLogin(username).enqueue(new Callback<List<CompanyLoginInfo>>() {
            private String[] tmpClients;

            @Override
            public void onResponse(Call<List<CompanyLoginInfo>> call, Response<List<CompanyLoginInfo>> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();
                List<CompanyLoginInfo> loginResponse = response.body();

                if(loginResponse != null) {
                    tmpClients = new String[loginResponse.size() + 1];


//                clientsHash.put(0, -1);
//                //loginResponse.get(0).getMSJ();
//                tmpClients[0] = "CLIENTE DE TIQUETE";
                    int i = 1;
                    for (CompanyLoginInfo c : loginResponse) {
                        addRow(c);
                        clientsList.add(c);
                        //tmpClients[i++] = c.getNombre();
                    }

                }
                utils.hideProgress();

            }

            @Override
            public void onFailure(Call<List<CompanyLoginInfo>> call, Throwable t) {
                utils.hideProgress();
            }
        });
    }

    public void goToMain(){
        Intent homepage = new Intent(getActivity(), DrawerActivity.class);
        startActivity(homepage);
        getActivity().finish();
    }

    public void addRow(CompanyLoginInfo client){

        TableRow row;

        if(client.getAlDia() == 1) {
            row = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.company, null);
        }
        else {
            row = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.companyblocked, null);
        }

        ((TextView)row.findViewById(R.id.empresa)).setText(client.getNombre());

        if(client.getAlDia() == 1) {
            ImageButton send = row.findViewById(R.id.ingresar);


            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TableRow r = (TableRow) (v.getParent().getParent());

                    int i = rows.lastIndexOf(r);

                    CompanyLoginInfo a = clientsList.get(i);

                    preferencesManager.saveInt(getActivity(), "IdEmpresa", a.getIdEmpresa());

                    String username = preferencesManager.getStringValue(mActivity, "username");

                    Log.d("User: ", username);
                    Log.d("Empresa: ", String.valueOf(a.getNombre()));
                    Log.d("Id: ", String.valueOf(a.getIdEmpresa()));
                    preferencesManager.saveString(getActivity(), "rememberUser", "OK");

                    goToMain();

                   // reenviarApi(new DecimalFormat("#").format(a.getIdFactura()) , new DecimalFormat("#").format(a.getIdCliente()) );


//                android.support.v4.app.Fragment fragment = new ClientFragment();
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("client",  clientsList.get(i));
//                fragment.setArguments(bundle);
//
//                mActivity.setFragment(fragment, 2);

                }
            });
        }
        rows.add(row);

        tableClients.addView(row);

        tableClients.requestLayout();

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
