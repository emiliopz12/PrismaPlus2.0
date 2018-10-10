package com.prismaplus.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.prismaplus.R;
import com.prismaplus.activities.BillingActivity;
import com.prismaplus.activities.ClientsActivity;
import com.prismaplus.entities.ClientInfo;
import com.prismaplus.entities.Detail;
import com.prismaplus.entities.ProductInfo;
import com.prismaplus.herlpers.PreferencesManager;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.prismaplus.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @BindView(R.id.tableClients)
    TableLayout tableClients;

    private OnFragmentInteractionListener mListener;

    public ClientListFragment() {
        // Required empty public constructor
        utils  = new Utils();

    }


    // TODO: Rename and change types and number of parameters
//    public static ClientListFragment newInstance(String param1, String param2) {
//        ClientListFragment fragment = new ClientListFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

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

    public void pupulateClients() {

        String IdEmpresa = String.valueOf(preferencesManager.getIntValue(getActivity(),"IdEmpresa"));

        //utils.showProgess(getActivity(),"cargando");

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

                    //tmpClients[i++] = c.getNombre();
                }


                // utils.hideProgress();

            }

            @Override
            public void onFailure(Call<List<ClientInfo>> call, Throwable t) {
                // utils.hideProgress();
            }
        });
    }


    public void addRow(ClientInfo client){

        TableRow row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerowclientlist, null);

        ((TextView)row.findViewById(R.id.codigo)).setText(client.getIdCliente());
        ((TextView)row.findViewById(R.id.id)).setText(client.getNombre());
        ((TextView)row.findViewById(R.id.email)).setText(client.getEmail());


        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mActivity.setFragment(new ClientFragment());
//
//                TableRow r = (TableRow) (v.getParent());
//
//                tableProducts.removeView(r);
//
//                int i = rows.lastIndexOf(r);
//
//                detailsList.remove(i);
//
//                rows.remove(r);
//
//                calculoTotalGeneral();
//
//                tableProducts.requestLayout();

            }
        });



        tableClients.addView(row);

        tableClients.requestLayout();

    }



}
