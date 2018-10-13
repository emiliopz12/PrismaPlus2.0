package com.prismaplus.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.activities.ClientsActivity;
import com.prismaplus.activities.ProductsActivity;
import com.prismaplus.entities.ClientInfo;
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


public class ProductsListFragment extends Fragment {
    View rootView;
    private final int HOME = 16908332;
    private ProductsActivity mActivity;

    private PreferencesManager preferencesManager;
    private ConnectionInterface connetionService;
    Utils utils;

    Map<Integer, String> clientsHash = new HashMap<>();;

    List<ProductInfo> productsList = new ArrayList<>();
    List<TableRow> rows = new ArrayList<TableRow>();


    @BindView(R.id.tableClients)
    TableLayout tableClients;

    @BindView(R.id.fab)
    FloatingActionButton fab;




    public ProductsListFragment() {
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
        mActivity.getSupportActionBar().setTitle("PRODUCTOS");

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
        rootView = inflater.inflate(R.layout.fragment_products_list, container, false);
        ButterKnife.bind(this,rootView);
        pupulateProducts();
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
        mActivity = (ProductsActivity) getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



    public void pupulateProducts() {

        String IdEmpresa = String.valueOf(preferencesManager.getIntValue(getActivity(),"IdEmpresa"));

        utils.showProgess(getActivity(),"Cargando");

        connetionService.getProduct(IdEmpresa, "t").enqueue(new Callback<List<ProductInfo>>() {
            private String[] tmpClients;

            @Override
            public void onResponse(Call<List<ProductInfo>> call, Response<List<ProductInfo>> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();
                List<ProductInfo> loginResponse = response.body();
                tmpClients = new String[loginResponse.size()+1];


//                clientsHash.put(0, -1);
//                //loginResponse.get(0).getMSJ();
//                tmpClients[0] = "CLIENTE DE TIQUETE";
                int i = 1;
                for(ProductInfo c : loginResponse){
                    clientsHash.put(i, c.getCodigoArticulo());
                    addRow(c);
                    productsList.add(c);
                    //tmpClients[i++] = c.getNombre();
                }


                utils.hideProgress();

            }

            @Override
            public void onFailure(Call<List<ProductInfo>> call, Throwable t) {
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

    public void addRow(ProductInfo client){

        TableRow row;

        int r = mod(lines, 2);

        lines++;

        if(r == 0)
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerowproductlist, null);
        else
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerowproductlistwhite, null);

        ((TextView)row.findViewById(R.id.codigo)).setText(client.getCodigoArticulo());
        ((TextView)row.findViewById(R.id.desc)).setText(client.getDescripcion());

        rows.add(row);

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TableRow r = (TableRow) (v);

                int i = rows.lastIndexOf(r);

                rows.remove(r);

                Fragment fragment = new ProductsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("client",  productsList.get(i));
                fragment.setArguments(bundle);

                mActivity.setFragment(fragment, 2);

            }
        });



        tableClients.addView(row);

        tableClients.requestLayout();

    }



}