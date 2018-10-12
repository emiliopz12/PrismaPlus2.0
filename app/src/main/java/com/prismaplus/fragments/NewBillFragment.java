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

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.activities.BillingActivity;
import com.prismaplus.activities.SplashActivity;
import com.prismaplus.entities.Bill;
import com.prismaplus.entities.BillInfo;
import com.prismaplus.entities.ClientInfo;
import com.prismaplus.entities.Detail;
import com.prismaplus.entities.LoginInfo;
import com.prismaplus.entities.ProductInfo;
import com.prismaplus.herlpers.PreferencesManager;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.prismaplus.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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

    private int lines = -1;
    Utils utils;


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

    /*@BindView(R.id.butgenerar)
    Button butgenerar;*/

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

    @BindView(R.id.TC)
    TextView TC;

    @BindView(R.id.genSub)
    TextView genSubtot;

    @BindView(R.id.genDesc)
    TextView genDesc;

    @BindView(R.id.genIV)
    TextView genIV;

    @BindView(R.id.genTotal)
    TextView genTot;

    @BindView(R.id.observations)
    TextView observations;

    private PreferencesManager preferencesManager;
    private ConnectionInterface connetionService;

    Map<Integer, String> conditionHash, situationHash, currencyHash, productsHash;

    Map<Integer, String> clientsHash;

    List<ProductInfo> productsList;
    List<ClientInfo> clientsList;
    List<Detail> detailsList  = new ArrayList<Detail>();

    Double localSub, localDesc, localIV, localTotal;

    ProductInfo actualProduct;

    List<ProductInfo> productsAddedList = new ArrayList<ProductInfo>();

    List<TableRow> rows = new ArrayList<TableRow>();

    public NewBillFragment() {
        // Required empty public constructor
        utils  = new Utils();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setTitle("NUEVO TIQUETE");
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

        spinner_situation.setEnabled(false);

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

        //utils.showProgess(getActivity(),"cargando");

        connetionService.getClients(IdEmpresa, 0).enqueue(new Callback<List<ClientInfo>>() {
            private String[] tmpClients;

            @Override
            public void onResponse(Call<List<ClientInfo>> call, Response<List<ClientInfo>> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();
                List<ClientInfo> loginResponse = response.body();
                tmpClients = new String[loginResponse.size()+1];


                clientsHash.put(0, "-1");
                //loginResponse.get(0).getMSJ();
                tmpClients[0] = "CLIENTE DE TIQUETE";
                int i = 1;
                for(ClientInfo c : loginResponse){
                    clientsHash.put(i, c.getIdCliente());
                    tmpClients[i++] = c.getNombre();
                }

                ArrayAdapter<String> spinnerConditionrrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tmpClients);

                //spinnerConditionrrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                spinner_client.setAdapter(spinnerConditionrrayAdapter);



               // utils.hideProgress();

            }

            @Override
            public void onFailure(Call<List<ClientInfo>> call, Throwable t) {
               // utils.hideProgress();
            }
        });
    }

    public void pupulateProducts() {

        String IdEmpresa = String.valueOf(preferencesManager.getIntValue(getActivity(),"IdEmpresa"));

        utils.showProgess(getActivity(),"Cargando");

//        connetionService.getProduct(IdEmpresa, "t").enqueue(new Callback<List<ProductInfo>>() {
        connetionService.getProduct(IdEmpresa, "t").enqueue(new Callback<List<ProductInfo>>() {
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
                utils.hideProgress();

            }

            @Override
            public void onFailure(Call<List<ProductInfo>> call, Throwable t) {

                Log.d("ERR: ", t.getMessage());
                utils.hideProgress();

            }
        });

    }

    public static float roundAvoid(double number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    @OnClick(R.id.add_row)
    public void addRow(){

        String preci = precio.getText().toString();
        String canti = cant.getText().toString();
        String descPorc = descPor.getText().toString();
        String descrip = description.getText().toString();
        String natDescu = natDesc.getText().toString();
        String moneda = spinner_currency.getSelectedItem().toString();
        String tc = TC.getText().toString();
        String tot = total.getText().toString();

        if( currencyHash.get(spinner_currency.getSelectedItemPosition()).equals("USD") && (tc.equals("") || Integer.parseInt(tc) == 0)){
            Toast.makeText(rootView.getContext(), "Debe ingresar TC", Toast.LENGTH_LONG).show();
            return;
        }

        if(preci.equals("") || Double.parseDouble(preci) < 1){
            Toast.makeText(rootView.getContext(), "Valor invalido en precio", Toast.LENGTH_LONG).show();
            return;
        }

        if(canti.equals("") || Integer.parseInt(canti) < 1){
            Toast.makeText(rootView.getContext(), "Valor invalido en cantidad", Toast.LENGTH_LONG).show();
            return;
        }

        if(tot.equals("") || Float.parseFloat(tot) < 1){
            Toast.makeText(rootView.getContext(), "Valor invalido en total", Toast.LENGTH_LONG).show();
            return;
        }

        if(descrip.equals("")){
            Toast.makeText(rootView.getContext(), "Debe ingresar desripcion", Toast.LENGTH_LONG).show();
            return;
        }

        if(Integer.parseInt(descPorc) > 0 && natDescu.equals("")){
            Toast.makeText(rootView.getContext(), "Debe ingresar Nat. Descuento", Toast.LENGTH_LONG).show();
            return;
        }



        if(lines == -1){
            tableProducts.removeViewAt(1);

            tableProducts.requestLayout();

            lines++;
        }

        TableRow row;

        int r = mod(lines, 2);

        Log.d("2: ", String.valueOf(r));


        if(r == 0)
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerow, null);
        else
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerowwhite, null);
        Detail detail = new Detail();

        ImageButton del = (ImageButton)row.findViewById(R.id.del);



        ((TextView)row.findViewById(R.id.cant)).setText(canti);
        ((TextView)row.findViewById(R.id.descripcion)).setText(descrip);
        ((TextView)row.findViewById(R.id.precio)).setText(preci);
        ((TextView)row.findViewById(R.id.total)).setText(tot);

        rows.add(row);

        lines++;

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TableRow r = (TableRow) (v.getParent());

                tableProducts.removeView(r);

                int i = rows.lastIndexOf(r);

                detailsList.remove(i);

                rows.remove(r);

                calculoTotalGeneral();

                tableProducts.requestLayout();

            }
        });

        detail.setDescripcion(descrip);
        detail.setTotalLinea(total.getText().toString());
        detail.setMontoImpuesto(montoIV.getText().toString());
        detail.setMontoDescuento(desc.getText().toString());
        //detail.setSubtotal(String.valueOf((Double.parseDouble(preci) - Double.parseDouble(montoIV.getText().toString()) ) * Integer.parseInt(canti)));
        detail.setSubtotal(String.valueOf(neto.getText().toString()));
        detail.setPrecioUnitario(preci);
        detail.setPorcentajeDescuento(descPorc);
        detail.setNaturalezaDescuento(natDescu);
        detail.setCodigoArticulo(actualProduct.getCodigoArticulo());
        detail.setCantidad(canti);
        detail.setMontoTotal(total.getText().toString());
//        if(Integer.parseInt(detail.getMontoImpuesto()) > 0){
//            detail.setEsGravado("1");
//        }
//        else{
//            detail.setEsGravado("0");
//        }

        detailsList.add(detail);

        calculoTotalGeneral();

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
            neto.setText(String.valueOf(prod.getPrecio()));

        }
        else {
            checkIV.setChecked(true);
            Double PorImpuesto= (1+ (prod.getPorcentajeImpuesto()/100));
            double Neto = (prod.getPrecio() / PorImpuesto) * Integer.valueOf(cant.getText().toString());

            Log.d("ROunded ", String.valueOf(roundAvoid(Neto, 2)));

            neto.setText(String.valueOf(roundAvoid(Neto, 2)));
        }

        //Log.d("POR IMPUESTO: ", String.valueOf(prod.getPorcentajeImpuesto()));

        //montoIV.setText(String.valueOf(prod.getPorcentajeImpuesto()));

        description.setText(prod.getDescripcion().toString());
        precio.setText(String.valueOf(prod.getPrecio()));

//        if(prod.getPorcentajeImpuesto() < 1)
//            neto.setText(String.valueOf(prod.getPrecio()));
//
//        else{
//
//            Double PorImpuesto= (1+ (prod.getPorcentajeImpuesto()/100));
//            double Neto = prod.getPrecio() / PorImpuesto;
//            neto.setText(String.valueOf(prod.getPrecio()));
//        }

    }

    @OnTextChanged(R.id.cant)
    public void newCant(CharSequence text){

        if(!text.toString().equals("") && !precio.getText().toString().equals("")) {

            //actualProduct.setPorcentajeImpuesto((double) 50);

            Double preci = Double.parseDouble(precio.getText().toString());

            Double PorImpuesto= (1+ (actualProduct.getPorcentajeImpuesto()/100));
            Double Neto = (preci / PorImpuesto) * Integer.valueOf(text.toString());
            neto.setText(String.valueOf(roundAvoid(Neto, 2)));

            PorImpuesto = (1 + (actualProduct.getPorcentajeImpuesto() / 100));
            Double ValorImpuesto = preci / PorImpuesto;
            Double IV = (preci - ValorImpuesto) * Integer.parseInt(text.toString());
            montoIV.setText(String.valueOf(roundAvoid(IV, 2)));

            calculoDescuento();
            calculoTotalLinea();
        }
    }

    @OnTextChanged(R.id.precio)
    public void newPrecio(CharSequence text){

        if(!text.toString().equals("")){

            //actualProduct.setPorcentajeImpuesto((double) 50);

            if(!cant.getText().toString().equals("")){
                Double preci = Double.parseDouble(text.toString());

                Double PorImpuesto= (1+ (actualProduct.getPorcentajeImpuesto()/100));
                Double Neto = (preci / PorImpuesto) * Integer.valueOf(cant.getText().toString());
                //neto.setText(String.valueOf(Neto));
                neto.setText(String.valueOf(roundAvoid(Neto, 2)));

                PorImpuesto = (1 + (actualProduct.getPorcentajeImpuesto() / 100));
                Double ValorImpuesto = preci / PorImpuesto;
                Double IV = (preci - ValorImpuesto) * Integer.parseInt(cant.getText().toString());
                //montoIV.setText(String.valueOf(IV));
                montoIV.setText(String.valueOf(roundAvoid(IV, 2)));

                calculoDescuento();
                calculoTotalLinea();
            }
        }

    }

    @OnTextChanged(R.id.descPor)
    public void newDesc(CharSequence text){

        if(!text.toString().equals("")){

            if(!cant.getText().toString().equals("") && !precio.getText().toString().equals("")){
                calculoDescuento();
                calculoTotalLinea();
            }
        }

    }

    public void calculoDescuento(){
        if(!cant.getText().toString().equals("") && !precio.getText().toString().equals("") && !descPor.getText().toString().equals("") ){
            Double preci = Double.parseDouble(precio.getText().toString());
            int canti = Integer.parseInt(cant.getText().toString());
            float descPorc = Float.parseFloat(descPor.getText().toString());

            Log.d("DESC PRE: ", String.valueOf(preci));
            Log.d("DESC CANT: ", String.valueOf(canti));
            Log.d("DESC POR: ", String.valueOf(descPorc));


            Double subt = preci * canti;
            Log.d("SUB 1: ", String.valueOf(subt));

            subt = subt - Double.parseDouble(montoIV.getText().toString());

            Log.d("SUB 2: ", String.valueOf(subt));
            float por = (descPorc / 100);

            Log.d("DESC POR 2: ", String.valueOf(por));

            subt = (subt * por);

            Log.d("DESC: ", String.valueOf(subt));
            desc.setText(String.valueOf(roundAvoid(subt, 2)));
            //desc.setText(String.valueOf(subt));
        }
    }

    public void calculoTotalLinea(){
        if(!cant.getText().toString().equals("") && !precio.getText().toString().equals("")) {
            Double preci = Double.parseDouble(precio.getText().toString());
            Integer canti = Integer.parseInt(cant.getText().toString());
            double descu = 0.0;
            if(!desc.getText().toString().equals(""))
                descu = roundAvoid(Double.parseDouble(desc.getText().toString()), 2);

            Double tot = (preci * canti) - descu;
            total.setText(String.valueOf(roundAvoid(tot, 2)));
        }
    }

    public void calculoTotalGeneral(){
        localDesc = localSub = localIV = localTotal = 0.0;
        if(detailsList.size() > 0) {


            for(Detail d: detailsList){
                localDesc += roundAvoid(Double.parseDouble(d.getMontoDescuento()),2);
                localSub += roundAvoid(Double.parseDouble(d.getSubtotal()), 2);
                localIV += roundAvoid(Double.parseDouble(d.getMontoImpuesto()), 2);
            }

            localTotal = localSub + localIV - localDesc;

            genDesc.setText("Descuento: "+roundAvoid(localDesc, 2));
            genSubtot.setText("Sub total: "+roundAvoid(localSub, 2));
            genIV.setText("IV: "+roundAvoid(localIV, 2));
            genTot.setText("Total: "+roundAvoid(localTotal, 2));
        }
        else{
            genDesc.setText("Descuento: 0");
            genSubtot.setText("Sub total: 0");
            genIV.setText("IV: 0");
            genTot.setText("Total: 0");
        }
    }

    @OnClick (R.id.fabCreate)
    public void generar() {

        if (detailsList.size() > 0) {

            Bill newBill = new Bill();

            String condicionVenta = conditionHash.get(spinnerCondition.getSelectedItemPosition()).toString();
            String situation = situationHash.get(spinner_situation.getSelectedItemPosition()).toString();
            String moneda = currencyHash.get(spinner_currency.getSelectedItemPosition()).toString();
            String IdClient = clientsHash.get(spinner_client.getSelectedItemPosition());

            String username = preferencesManager.getStringValue(getActivity(), "username");
            int IdEmpresa = preferencesManager.getIntValue(getActivity(), "IdEmpresa");

            Log.d("USERNAME", username);

            int i = 1;

            for (Detail d : detailsList) {
                d.setNumeroLinea(String.valueOf(i++));
            }

            newBill.setIdempresa(String.valueOf(IdEmpresa));
            newBill.setIdCliente(String.valueOf(IdClient));
            newBill.setCondicionVenta(condicionVenta);
            newBill.setSituacion(situation);
            newBill.setMoneda(moneda);
            newBill.setDetail(detailsList);
            newBill.setTipoCambio(TC.getText().toString());
            newBill.setFormaPago(spinner_pay.getSelectedItem().toString());
            newBill.setObservaciones(observations.getText().toString());
            newBill.setTipoAccion("");
            newBill.setIdFactura("0");
            newBill.setUsuario(username);

            String msj = String.format("{Empresa: %s, IdCliente: %s, CondicionVenta: %s, Situacion: %s, Moneda: %s, " +
                            "TipoCambio: %s, FormaPago: %s, Observaciones: %s, Detalle: %s, TipoAccion: %s, IdFactura: 0, Usuario: %s}",
                    newBill.getIdempresa(),
                    newBill.getIdCliente(),
                    newBill.getCondicionVenta(),
                    newBill.getSituacion(),
                    newBill.getMoneda(),
                    newBill.getTipoCambio(),
                    newBill.getFormaPago(),
                    newBill.getObservaciones(),
                    newBill.getDetail().toString(),
                    newBill.getTipoAccion(),
                    newBill.getIdFactura(),
                    username);

            Log.d("BILL", msj);

            utils.showProgess(getActivity(),"Procesando");
            connetionService.doBill(newBill).enqueue(new Callback<BillInfo>() {

                @Override
                public void onResponse(Call<BillInfo> call, Response<BillInfo> response) {
                    //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();
                    BillInfo res = response.body();

                    utils.hideProgress();

                    Log.d("NULL: ", response.message());
                    Log.d("CODE: ", String.valueOf(response.code()));
                    Log.d("IsSuccessful: ", String.valueOf(response.isSuccessful()));

                    if(response.code() == 500){
                        new MaterialDialog.Builder(rootView.getContext())
                                .title("Error")
                                .content("Ocurrió un error")
                                .contentGravity(GravityEnum.CENTER)
                                .positiveText("Aceptar")
                                .onPositive((dialog, which) -> {
                                })
                                .show();
                    }
                    else {
                        new MaterialDialog.Builder(rootView.getContext())
                                .title("Mensaje")
                                .content("Se ha enviado la factura satisfactoriamente")
                                .contentGravity(GravityEnum.CENTER)
                                .positiveText("Aceptar")
                                .onPositive((dialog, which) -> {
                                    cleanFields();
                                })
                                .show();
                    }

//                    if (res == null) {
//                        Toast.makeText(rootView.getContext(), "NULL", Toast.LENGTH_LONG).show();
//
//                    } else
//                        Toast.makeText(rootView.getContext(), res.getMSJ(), Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(Call<BillInfo> call, Throwable t) {

                    utils.hideProgress();

                    if(t.getMessage().equals("timeout")){
                        new MaterialDialog.Builder(rootView.getContext())
                                .title("Error")
                                .content("Ocurrió un error")
                                .contentGravity(GravityEnum.CENTER)
                                .positiveText("Aceptar")
                                .onPositive((dialog, which) -> {
                                })
                                .show();
                    }
                    else{
                        new MaterialDialog.Builder(rootView.getContext())
                                .title("Mensaje")
                                .content("Se ha enviado la factura satisfactoriamente")
                                .contentGravity(GravityEnum.CENTER)
                                .positiveText("Aceptar")
                                .onPositive((dialog, which) -> {
                                    cleanFields();
                                })
                                .show();
                    }
                    Log.d("ERR: ", t.getMessage());

                }
            });


        }
    }

    private int mod(int x, int y)
    {
        int result = x % y;
        Log.d("!: ", String.valueOf(result));
        return result < 0? result + y : result;
    }

    public void cleanFields(){

        spinner_client.setSelection(0);
        spinnerCondition.setSelection(0);
        TC.setText("0");
        spinner_item.setSelection(0);
        description.setText("");
        cant.setText("1");
//        neto.setText("0");
//        precio.setText("0");
        montoIV.setText("0");
        descPor.setText("0");
        desc.setText("0");
        natDesc.setText("");
        total.setText("0");

        tableProducts.removeViews(1, rows.size());

        detailsList = new ArrayList<>();
        rows = new ArrayList<>();
        lines = -1;

        TableRow row;

        int r = mod(lines+1, 2);

        Log.d("2: ", String.valueOf(r));


        if(r == 0)
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerow, null);
        else
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerowwhite, null);

        ImageButton del = (ImageButton)row.findViewById(R.id.del);

        ((TextView)row.findViewById(R.id.cant)).setText("");
        ((TextView)row.findViewById(R.id.descripcion)).setText("");
        ((TextView)row.findViewById(R.id.precio)).setText("");
        ((TextView)row.findViewById(R.id.total)).setText("");

        tableProducts.addView(row);

        tableProducts.requestLayout();

        observations.setText("");
        spinner_pay.setSelection(0);
        genSubtot.setText("Sub Total: 0");
        genDesc.setText("Descuento: 0");
        genIV.setText("IV: 0");
        genTot.setText("Total: 0");


    }

}
