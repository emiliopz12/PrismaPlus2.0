package com.prismaplus.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.activities.BillingActivity;
import com.prismaplus.activities.ClientsActivity;
import com.prismaplus.activities.ProductsActivity;
import com.prismaplus.entities.ClientInfo;
import com.prismaplus.entities.Detail;
import com.prismaplus.entities.ProductInfo;
import com.prismaplus.herlpers.PreferencesManager;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.prismaplus.utils.Utils;

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


public class ClientListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    View rootView;
    private final int HOME = 16908332;
    private ClientsActivity mActivity;

    private PreferencesManager preferencesManager;
    private ConnectionInterface connetionService;
    Utils utils;

    Map<Integer, String> clientsHash = new HashMap<>();;

    List<ClientInfo> clientsList = new ArrayList<>();
    List<TableRow> rows = new ArrayList<TableRow>();


    @BindView(R.id.tableClients)
    TableLayout tableClients;

    @BindView(R.id.fab)
    FloatingActionButton fab;




    public ClientListFragment() {
        // Required empty public constructor
        utils  = new Utils();

    }

    @OnClick(R.id.fab)
    public void goNewClient() {
        mActivity.setFragment(new ClientFragment(), 2);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setTitle("CLIENTES");

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
        rootView = inflater.inflate(R.layout.fragment_client_list, container, false);
        ButterKnife.bind(this,rootView);
        pupulateClients();
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
        mActivity = (ClientsActivity) getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



    public void pupulateClients() {

        String IdEmpresa = String.valueOf(preferencesManager.getIntValue(getActivity(),"IdEmpresa"));

        utils.showProgess(getActivity(),"Cargando");

        connetionService.getClients(IdEmpresa, 0).enqueue(new Callback<List<ClientInfo>>() {
            private String[] tmpClients;

            @Override
            public void onResponse(Call<List<ClientInfo>> call, Response<List<ClientInfo>> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();
                List<ClientInfo> loginResponse = response.body();
                tmpClients = new String[loginResponse.size()+1];


//                clientsHash.put(0, -1);
//                //loginResponse.get(0).getMSJ();
//                tmpClients[0] = "CLIENTE DE TIQUETE";
                int i = 1;
                for(ClientInfo c : loginResponse){
                    clientsHash.put(i, c.getIdCliente());
                    addRow(c);
                    clientsList.add(c);
                    //tmpClients[i++] = c.getNombre();
                }


                 utils.hideProgress();

            }

            @Override
            public void onFailure(Call<List<ClientInfo>> call, Throwable t) {
                 utils.hideProgress();
            }
        });
    }

    private int mod(int x, int y)
    {
        int result = x % y;
        Log.d("!: ", String.valueOf(result));
        return result < 0? result + y : result;
    }

    int lines = 0;

    public void addRow(ClientInfo client){

        TableRow row;

        int r = mod(lines, 2);

        lines++;

        if(r == 0)
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerowclientlist, null);
        else
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerowclientlistwhite, null);

        ((TextView)row.findViewById(R.id.codigo)).setText(client.getIdCliente());
        ((TextView)row.findViewById(R.id.id)).setText(client.getNombre());
        ((TextView)row.findViewById(R.id.email)).setText(client.getEmail());

        rows.add(row);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TableRow r = (TableRow) (v);

                int i = rows.lastIndexOf(r);

                rows.remove(r);

                Fragment fragment = new ClientFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("client",  clientsList.get(i));
                fragment.setArguments(bundle);

                mActivity.setFragment(fragment, 2);

            }
        });



        tableClients.addView(row);

        tableClients.requestLayout();

    }



}
