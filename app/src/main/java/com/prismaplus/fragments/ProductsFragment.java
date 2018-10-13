package com.prismaplus.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.prismaplus.R;
import com.prismaplus.activities.ClientsActivity;
import com.prismaplus.activities.ProductsActivity;
import com.prismaplus.entities.ClientInfo;
import com.prismaplus.entities.ProductInfo;
import com.prismaplus.entities.UnidadInfo;
import com.prismaplus.herlpers.PreferencesManager;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.prismaplus.utils.Utils;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragment extends Fragment {
    View rootView;
    private ProductsActivity mActivity;
    private PreferencesManager preferencesManager;
    private ConnectionInterface connetionService;
    Utils utils;
    private final int HOME = 16908332;
    List<UnidadInfo> unitList;

    @BindView(R.id.spinner_id)
    Spinner spinner_id;

    @BindView(R.id.spinner_state)
    Spinner spinner_state;

    @BindView(R.id.spinner_imp)
    Spinner spinner_imp;

    @BindView(R.id.spinner_uni)
    Spinner spinner_uni;

    @BindView(R.id.spinner_es_servicio)
    Spinner spinner_es_servicio;

    @BindView(R.id.desc)
    EditText desc;

    @BindView(R.id.id)
    EditText id;

    @BindView(R.id.precio)
    EditText precio;

    @BindView(R.id.imp)
    EditText imp;

    ProductInfo client;
    private HashMap<Object,Object> tipoCodigoHash, tipoImpuestoHash, invTipoCodigoHash, invTipoImpuestoHash, unitHash;

    public ProductsFragment() {
        // Required empty public constructor
        utils  = new Utils();

    }

    public void populateHashes() {

        tipoCodigoHash = invTipoCodigoHash = invTipoImpuestoHash =  unitHash = new HashMap<>();
        tipoImpuestoHash= new HashMap<>();

        /* **** CONDITION HASH ****/

        tipoCodigoHash.put(0, "01");
        tipoCodigoHash.put(1, "02");
        tipoCodigoHash.put(2, "03");
        tipoCodigoHash.put(3, "04");
        tipoCodigoHash.put(4, "99");

        invTipoCodigoHash.put("99", 4);
        invTipoCodigoHash.put("04", 3);
        invTipoCodigoHash.put("03", 2);
        invTipoCodigoHash.put("02", 1);
        invTipoCodigoHash.put("01", 0);

        /* **** SITUATION HASH ****/

        tipoImpuestoHash.put(0, "00");
        tipoImpuestoHash.put(1, "01");
        tipoImpuestoHash.put(2, "02");
        tipoImpuestoHash.put(3, "03");
        tipoImpuestoHash.put(4, "04");
        tipoImpuestoHash.put(5, "05");
        tipoImpuestoHash.put(6, "06");
        tipoImpuestoHash.put(7, "07");
        tipoImpuestoHash.put(8, "12");
        tipoImpuestoHash.put(9, "98");

        invTipoImpuestoHash.put("98", 9);
        invTipoImpuestoHash.put("12", 8);
        invTipoImpuestoHash.put("07", 7);
        invTipoImpuestoHash.put("06", 6);
        invTipoImpuestoHash.put("05", 5);
        invTipoImpuestoHash.put("04", 4);
        invTipoImpuestoHash.put("03", 3);
        invTipoImpuestoHash.put("02", 2);
        invTipoImpuestoHash.put("01", 1);
        invTipoImpuestoHash.put("00", 0);



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setHasOptionsMenu(true);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setTitle("CREAR PRODUCTO");

        preferencesManager = PreferencesManager.getInstance();
        connetionService = ConnetionService.obtenerServicio(preferencesManager.getStringValue(getActivity(),"url").equals("pruebas") ? utils.URL_PRUEBAS : utils.URL_PROD);
        populateHashes();

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

        rootView = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this,rootView);

        ArrayAdapter<String> spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.typeid));
        spinner_id.setAdapter(spinnerConditionrrayAdapter);

        spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.state));
        spinner_state.setAdapter(spinnerConditionrrayAdapter);

        spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.typeimp));
        spinner_imp.setAdapter(spinnerConditionrrayAdapter);

        spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.servicio));
        spinner_es_servicio.setAdapter(spinnerConditionrrayAdapter);




//
//
        if(this.getArguments() != null && !this.getArguments().isEmpty()) {
            mActivity.getSupportActionBar().setTitle("ACTUALIZAR PRODUCTO");

            client = this.getArguments().getParcelable("client");


            desc.setText(client.getDescripcion());
            id.setText(client.getCodigoArticulo());
            precio.setText(String.valueOf(client.getPrecio()));
            imp.setText(String.valueOf(client.getPorcentajeImpuesto()));

            spinner_es_servicio.setSelection( client.getEsServicio() == 0 ? 1 : 0);
            spinner_imp.setSelection( (Integer) invTipoImpuestoHash.get( client.getCodigoImpuesto() ));
            spinner_id.setSelection( (Integer) invTipoCodigoHash.get( client.getTipoCodigo() ));

            if (client.getEstado() == 1)
                spinner_state.setSelection(0);
            else
                spinner_state.setSelection(1);

            Log.d("Client", client.getCodigoArticulo());
        }
        else
            client = new ProductInfo();

        connetionService.getUnidad().enqueue(new Callback<List<UnidadInfo>>() {
            private String[] tmpProducts;

            @Override
            public void onResponse(Call<List<UnidadInfo>> call, Response<List<UnidadInfo>> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();
                List<UnidadInfo> loginResponse = response.body();
                unitList = loginResponse;
                tmpProducts = new String[loginResponse.size()];
                //loginResponse.get(0).getMSJ();
                int i = 0;
                for(UnidadInfo c : loginResponse){
                    tmpProducts[i++] = c.getDescripcion();
                    unitHash.put(c.getCodigoUnidad(), i-1);
                }

                ArrayAdapter<String> spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tmpProducts);
                spinner_uni.setAdapter(spinnerConditionrrayAdapter);
                // utils.hideProgress();
                if(client.getUnidadDeMedida() != null)
                    spinner_uni.setSelection( (Integer) unitHash.get( client.getUnidadDeMedida() ));

            }

            @Override
            public void onFailure(Call<List<UnidadInfo>> call, Throwable t) {

                Log.d("ERR: ", t.getMessage());
                //utils.hideProgress();

            }
        });


        return  rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case HOME:
                mActivity.setFragment(new ProductsListFragment(), 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.fabAdd)
    public void saveClient() {


        utils.showProgess(getActivity(),"Procesando");

        client.setDescripcion(desc.getText().toString());
        client.setCodigoArticulo(id.getText().toString());
        client.setPrecio(Double.valueOf(precio.getText().toString()));
        client.setTipoCodigo(String.valueOf(tipoCodigoHash.get(spinner_id.getSelectedItemPosition())));
        client.setCodigoImpuesto(String.valueOf(tipoImpuestoHash.get(spinner_imp.getSelectedItemPosition())));
        client.setPorcentajeImpuesto(Double.parseDouble(imp.getText().toString()));

        client.setEsServicio(spinner_es_servicio.getSelectedItemPosition() == 0 ? 1 : 0);

        client.setUnidadDeMedida(unitList.get(spinner_uni.getSelectedItemPosition()).getCodigoUnidad());
        client.setUnidadMedidaDsc(unitList.get(spinner_uni.getSelectedItemPosition()).getDescripcion());


        String typeId = spinner_id.getSelectedItem().toString();

        Log.d("TypeID: ", typeId);

        client.setEstado(spinner_state.getSelectedItemPosition() == 0 ? 1: 0);

        String IdEmpresa = String.valueOf(preferencesManager.getIntValue(getActivity(),"IdEmpresa"));
        //client.setIdEmpresa(IdEmpresa);

        connetionService.saveProduct(client).enqueue(new Callback<ProductInfo>() {

            @Override
            public void onResponse(Call<ProductInfo> call, Response<ProductInfo> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();

                mActivity.setFragment(new ProductsListFragment(), 1);

                utils.hideProgress();


            }

            @Override
            public void onFailure(Call<ProductInfo> call, Throwable t) {

                mActivity.setFragment(new ProductsListFragment(), 1);

                utils.hideProgress();
            }
        });

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


}