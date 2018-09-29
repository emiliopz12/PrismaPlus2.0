package com.prismaplus.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.activities.BillingActivity;
import com.prismaplus.activities.SplashActivity;
import com.prismaplus.entities.Bill;
import com.prismaplus.entities.ClientInfo;
import com.prismaplus.entities.Detail;
import com.prismaplus.entities.LoginInfo;
import com.prismaplus.entities.ProductInfo;
import com.prismaplus.herlpers.PreferencesManager;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnTextChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewBillFragment extends Fragment {

    View rootView;
    private final int HOME = 16908332;
    private BillingActivity mActivity;

    private int lines = 0;

    @BindView(R.id.spinner_pay)
    Spinner spinner_pay;

    @BindView(R.id.spinner_condition)
    Spinner spinnerCondition;

    @BindView(R.id.spinner_client)
    Spinner spinner_client;

    @BindView(R.id.spinner_situation)
    Spinner spinner_situation;

    @BindView(R.id.spinner_currency)
    Spinner spinner_currency;

    @BindView(R.id.spinner_item)
    Spinner spinner_item;

    @BindView(R.id.add_row)
    ImageButton add_row;

    @BindView(R.id.tableProducts)
    TableLayout tableProducts;


    @BindView(R.id.checkIV)
    CheckBox checkIV;

    @BindView(R.id.butgenerar)
    Button butgenerar;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.precio)
    TextView precio;

    @BindView(R.id.neto)
    TextView neto;

    @BindView(R.id.cant)
    TextView cant;

    @BindView(R.id.montoIV)
    TextView montoIV;

    @BindView(R.id.descPor)
    TextView descPor;

    @BindView(R.id.desc)
    TextView desc;

    @BindView(R.id.natDesc)
    TextView natDesc;

    @BindView(R.id.total)
    TextView total;

    private PreferencesManager preferencesManager;
    private ConnectionInterface connetionService;

    Map<Integer, String> clientsHash,
    conditionHash, situationHash, currencyHash, productsHash;

    List<ProductInfo> productsList;
    List<ClientInfo> clientsList;

    ProductInfo actualProduct;

    public NewBillFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setTitle("NUEVO TIQUETE");
        connetionService = ConnetionService.obtenerServicio();
        preferencesManager = PreferencesManager.getInstance();

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
        rootView = inflater.inflate(R.layout.fragment_new_bill, container, false);
        ButterKnife.bind(this,rootView);
        ArrayAdapter<String> spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_condition));
        spinnerCondition.setAdapter(spinnerConditionrrayAdapter);

        spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.pay_type));
        spinner_pay.setAdapter(spinnerConditionrrayAdapter);

        spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.currency));
        spinner_currency.setAdapter(spinnerConditionrrayAdapter);

        spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.situation));
        spinner_situation.setAdapter(spinnerConditionrrayAdapter);

        pupulateClients();

        pupulateProducts();

        populateHashes();

        return  rootView;
    }

    public void populateHashes() {

        clientsHash = new HashMap<>();
        conditionHash= new HashMap<>();
        situationHash = new HashMap<>();
        currencyHash = new HashMap<>();
        productsHash = new HashMap<>();

        /* **** CONDITION HASH ****/

        conditionHash.put(0, "01");
        conditionHash.put(1, "02");
        conditionHash.put(2, "03");
        conditionHash.put(3, "04");
        conditionHash.put(4, "05");
        conditionHash.put(5, "06");
        conditionHash.put(6, "99");

        /* **** SITUATION HASH ****/

        situationHash.put(0, "1");
        situationHash.put(1, "2");
        situationHash.put(2, "3");

        /* **** CURRENCY HASH ****/

        currencyHash.put(0, "CRC");
        currencyHash.put(1, "USD");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BillingActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Log.d("id", String.valueOf(item.getItemId()));
        switch (item.getItemId()) {
            case HOME:
                Intent homepage = new Intent(mActivity, DrawerActivity.class);
                startActivity(homepage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void pupulateClients() {

        String IdEmpresa = String.valueOf(preferencesManager.getIntValue(getActivity(),"IdEmpresa"));

        connetionService.getClients(IdEmpresa, 0).enqueue(new Callback<List<ClientInfo>>() {
            private String[] tmpClients;

            @Override
            public void onResponse(Call<List<ClientInfo>> call, Response<List<ClientInfo>> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();
                List<ClientInfo> loginResponse = response.body();
                tmpClients = new String[loginResponse.size()+1];
                //loginResponse.get(0).getMSJ();
                tmpClients[0] = "CLIENTE DE TIQUETE";
                int i = 1;
                for(ClientInfo c : loginResponse){
                    tmpClients[i++] = c.getNombre();
                }

                ArrayAdapter<String> spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tmpClients);
                spinner_client.setAdapter(spinnerConditionrrayAdapter);

            }

            @Override
            public void onFailure(Call<List<ClientInfo>> call, Throwable t) {

            }
        });
    }

    public void pupulateProducts() {

        String IdEmpresa = String.valueOf(preferencesManager.getIntValue(getActivity(),"IdEmpresa"));

        connetionService.getProduct(IdEmpresa, "0").enqueue(new Callback<List<ProductInfo>>() {
            private String[] tmpProducts;

            @Override
            public void onResponse(Call<List<ProductInfo>> call, Response<List<ProductInfo>> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();
                List<ProductInfo> loginResponse = response.body();
                productsList = loginResponse;
                tmpProducts = new String[loginResponse.size()];
                //loginResponse.get(0).getMSJ();
                int i = 0;
                for(ProductInfo c : loginResponse){
                    tmpProducts[i++] = c.getDescripcion();
                }

                ArrayAdapter<String> spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tmpProducts);
                spinner_item.setAdapter(spinnerConditionrrayAdapter);

            }

            @Override
            public void onFailure(Call<List<ProductInfo>> call, Throwable t) {

            }
        });

    }

    @OnClick(R.id.add_row)
    public void addRow(){

        if(lines == 0){
            tableProducts.removeViewAt(1);

            tableProducts.requestLayout();
        }

        TableRow row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerow, null);

        ImageButton del = (ImageButton)row.findViewById(R.id.del);

        ((TextView)row.findViewById(R.id.cant)).setText("1");
        ((TextView)row.findViewById(R.id.descripcion)).setText("hola");
        ((TextView)row.findViewById(R.id.precio)).setText("9000");
        ((TextView)row.findViewById(R.id.total)).setText("9000");
        del.setContentDescription("1");

        lines++;

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableProducts.removeViewAt(Integer.parseInt(v.getContentDescription().toString()));

                tableProducts.requestLayout();

            }
        });


        tableProducts.addView(row);

        tableProducts.requestLayout();

    }

    @OnItemSelected(R.id.spinner_client)
    public void changeTitle(Spinner spinner, int position) {

        if(position == 0){
            mActivity.getSupportActionBar().setTitle("NUEVO TIQUETE");
        }
        else
            mActivity.getSupportActionBar().setTitle("NUEVA FACTURA");

    }

    @OnItemSelected(R.id.spinner_condition)
    public void seeVal(Spinner spinner, int position) {

        //Toast.makeText(rootView.getContext(), this.conditionHash.get(position), Toast.LENGTH_LONG).show();

    }

    @OnItemSelected(R.id.spinner_item)
    public void productsChange(Spinner spinner, int position) {

        ProductInfo prod = productsList.get(position);

        actualProduct = prod;

        if(prod.getCodigoImpuesto().equals("00")){
            checkIV.setChecked(false);
        }
        else
            checkIV.setChecked(true);

        description.setText(prod.getDescripcion().toString());
        precio.setText(prod.getPrecio().toString());

        if(prod.getPorcentajeImpuesto() == 0)
            neto.setText(prod.getPrecio().toString());

        else{

//            float PorImpuesto= (1+ (prod.getPorcentajeImpuesto()/100));
//            float ValorImpuesto = prod.getPrecio()  / PorImpuesto;
//            float IV= (prod.getPrecio() - ValorImpuesto) * Integer.parseInt(cant.getText().toString());
//            montoIV.setText(String.valueOf(IV));

            float PorImpuesto= (1+ (prod.getPorcentajeImpuesto()/100));
            float Neto = prod.getPrecio() / PorImpuesto;
            neto.setText(prod.getPrecio().toString());
        }

    }

    @OnTextChanged(R.id.cant)
    public void newCant(CharSequence text){

        if(!text.toString().equals("") && !precio.getText().toString().equals("")) {

            actualProduct.setPorcentajeImpuesto(50);

            float preci = Float.parseFloat(precio.getText().toString());

            float PorImpuesto= (1+ (actualProduct.getPorcentajeImpuesto()/100));
            float Neto = preci / PorImpuesto;
            neto.setText(String.valueOf(Neto));

            PorImpuesto = (1 + (actualProduct.getPorcentajeImpuesto() / 100));
            float ValorImpuesto = preci / PorImpuesto;
            float IV = (preci - ValorImpuesto) * Integer.parseInt(text.toString());
            montoIV.setText(String.valueOf(IV));

            calculoDescuento();
            calculoTotalLinea();
        }
    }

    @OnTextChanged(R.id.precio)
    public void newPrecio(CharSequence text){

        if(!text.toString().equals("")){

            actualProduct.setPorcentajeImpuesto(50);

            if(!cant.getText().toString().equals("")){
                float preci = Float.parseFloat(text.toString());

                float PorImpuesto= (1+ (actualProduct.getPorcentajeImpuesto()/100));
                float Neto = preci / PorImpuesto;
                neto.setText(String.valueOf(Neto));

                PorImpuesto = (1 + (actualProduct.getPorcentajeImpuesto() / 100));
                float ValorImpuesto = preci / PorImpuesto;
                float IV = (preci - ValorImpuesto) * Integer.parseInt(cant.getText().toString());
                montoIV.setText(String.valueOf(IV));

                calculoDescuento();
                calculoTotalLinea();
            }
        }

    }

    @OnTextChanged(R.id.descPor)
    public void newDesc(CharSequence text){

        Toast.makeText(rootView.getContext(), "555", Toast.LENGTH_LONG).show();

        if(!text.toString().equals("")){

            Toast.makeText(rootView.getContext(), "555", Toast.LENGTH_LONG).show();

            if(!cant.getText().toString().equals("") && !precio.getText().toString().equals("")){
                calculoDescuento();
                calculoTotalLinea();
            }
        }

    }


    public void calculoDescuento(){
        if(!cant.getText().toString().equals("") && !precio.getText().toString().equals("") && !descPor.getText().toString().equals("") ){
            float preci = Float.parseFloat(precio.getText().toString());
            int canti = Integer.parseInt(cant.getText().toString());
            float descPorc = Float.parseFloat(descPor.getText().toString());

            float subt= preci * canti;
            subt = subt - Float.parseFloat(montoIV.getText().toString());
            subt = subt * (descPorc / 100);

            desc.setText(String.valueOf(subt));
        }
    }

    public void calculoTotalLinea(){
        if(!cant.getText().toString().equals("") && !precio.getText().toString().equals("")) {
            float preci = Float.parseFloat(precio.getText().toString());
            int canti = Integer.parseInt(cant.getText().toString());
            float descu = 0;
            if(!desc.getText().toString().equals(""))
                 descu = Float.parseFloat(desc.getText().toString());

            float tot = (preci * canti) - descu;
            total.setText(String.valueOf(tot));
        }
    }

    @OnClick (R.id.butgenerar)
    public void generar() {

        Bill newBill;

        Detail tmpDetail;



    }

}
