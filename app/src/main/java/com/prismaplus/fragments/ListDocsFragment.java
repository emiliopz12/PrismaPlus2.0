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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.activities.ClientsActivity;
import com.prismaplus.activities.ListDocActivity;
import com.prismaplus.entities.ClientInfo;
import com.prismaplus.entities.ListDocsInfo;
import com.prismaplus.entities.ProductInfo;
import com.prismaplus.herlpers.PreferencesManager;
import com.prismaplus.prisma.MainActivity;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.prismaplus.utils.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.sql.ClientInfoStatus;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListDocsFragment extends Fragment implements DatePickerDialog.OnDateSetListener {


    @BindView(R.id.tableClients)
    TableLayout tableClients;


    @BindView(R.id.textInicio)
    TextView textInicio;


    @BindView(R.id.textFin)
    TextView textFin;

    int lines = 0;
    int hasTouch = 0;
    View rootView;
    private ListDocActivity mActivity;
    private PreferencesManager preferencesManager;
    private ConnectionInterface connetionService;
    Utils utils;
    private final int HOME = 16908332;

    String fechaIncio = "";
    String fechaFin = "";
    private Calendar calendar;
    private int year, month, day;


    List<ListDocsInfo> lisDocList = new ArrayList<>();
    List<TableRow> rows = new ArrayList<TableRow>();

    public ListDocsFragment() {
        // Required empty public constructor
        utils = new Utils();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_list_docs, container, false);
        ButterKnife.bind(this,rootView);
      //  pupulateListDocs();

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        fechaIncio = day  + "/" + (month +1)  + "/" + year;
        textInicio.setText(fechaIncio);

        fechaFin = day  + "/" + (month +1)  + "/" + year;
        textFin.setText(fechaFin);

        return rootView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setHasOptionsMenu(true);
      //  mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      // mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setTitle("LISTADO DE DOCUMENTOS");

        preferencesManager = PreferencesManager.getInstance();
        connetionService = ConnetionService.obtenerServicio(preferencesManager.getStringValue(getActivity(),"url").equals("pruebas") ? utils.URL_PRUEBAS : utils.URL_PROD);

        try {
            mActivity.getSupportActionBar().setIcon(R.drawable.ic_prisma_big);
        }catch (Exception e){
            mActivity.getSupportActionBar().setIcon(R.drawable.ic_prisma);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
       switch (hasTouch){
           case 1 : fechaIncio = dayOfMonth  + "/" + (monthOfYear +1)  + "/" + year;
                    textInicio.setText(fechaIncio);
           break;
           case 2 : fechaFin = dayOfMonth  + "/" + (monthOfYear +1)  + "/" + year;
                    textFin.setText(fechaFin);
           break;
       }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
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


    @OnClick(R.id.relativeInit)
    public void choose(){
        hasTouch = 1;
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                ListDocsFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(mActivity.getFragmentManager(), "Datepickerdialog");
    }

    @OnClick(R.id.relativeFin)
    public void choose2(){
        hasTouch = 2;
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                ListDocsFragment.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(mActivity.getFragmentManager(), "Datepickerdialog");
    }

    @OnClick(R.id.buttonFind)
    public void cfind(){
        if(!fechaFin.equals("") && !fechaIncio.equals("")){
            pupulateListDocs();
        }else {
            //Toast.makeText(mActivity,"Las fechas no pueden ser vacías", Toast.LENGTH_LONG).show();
            new MaterialDialog.Builder(rootView.getContext())
                    .title("Error")
                    .content("Las fechas no pueden ser vacías")
                    .contentGravity(GravityEnum.CENTER)
                    .positiveText("Aceptar")
                    .onPositive((dialog, which) -> {
                    })
                    .show();
        }
    }

    public void pupulateListDocs() {

        String IdEmpresa = String.valueOf(preferencesManager.getIntValue(getActivity(),"IdEmpresa"));

        utils.showProgess(getActivity(),"Cargando");

        if(lisDocList.size() > 0) {
            lisDocList = new ArrayList<>();

            rows = new ArrayList<TableRow>();

            tableClients.removeViews(1, tableClients.getChildCount()-1);

            tableClients.requestLayout();
        }

        lines = 0;

        connetionService.getFacturasListado(IdEmpresa, fechaIncio,fechaFin,"T",0).enqueue(new Callback<List<ListDocsInfo>>() {
            private String[] tmpClients;

            @Override
            public void onResponse(Call<List<ListDocsInfo>> call, Response<List<ListDocsInfo>> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();
                List<ListDocsInfo> loginResponse = response.body();

//                tmpClients = new String[loginResponse.size()+1];
                int i = 1;
                for(ListDocsInfo c : loginResponse){
                    //clientsHash.put(i, c.getCodigoArticulo());
                    addRow(c);
                    lisDocList.add(c);
                    //tmpClients[i++] = c.getNombre();
                }
                utils.hideProgress();
            }

            @Override
            public void onFailure(Call<List<ListDocsInfo>> call, Throwable t) {
                utils.hideProgress();
            }
        });


    }


    public void addRow(ListDocsInfo client){

        TableRow row;

        int r = mod(lines, 2);

        lines++;

        if(r == 0)
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.listdetails, null);
        else
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.whitelistdetails, null);

        ((TextView)row.findViewById(R.id.numberDoc)).setText(client.getConsecutivo());
        ((TextView)row.findViewById(R.id.type)).setText(client.TipoDocumentoDsc);
        ((TextView)row.findViewById(R.id.state)).setText(client.Estado);
        //((TextView)row.findViewById(R.id.date)).setText(client.Descripcion != null ? client.Descripcion : "");
        ((TextView)row.findViewById(R.id.date)).setText(client.getFecha());
        ((TextView)row.findViewById(R.id.client)).setText(client.NombreCliente);
        ((TextView)row.findViewById(R.id.totaA)).setText(String.valueOf(client.getTotalComprobante()));

        rows.add(row);

        ImageButton send = (ImageButton)row.findViewById(R.id.send);
        ImageButton pdfPrint = (ImageButton)row.findViewById(R.id.pdfPrint);

        pdfPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TableRow r = (TableRow) (v.getParent());

                int i = rows.lastIndexOf(r);

                String idFacturar = ((TextView)(r.getChildAt(0))).getText().toString();

                String cliente = ((TextView)(r.getChildAt(4))).getText().toString();

                ListDocsInfo a = lisDocList.get(i);
                Log.d("Factura: ", String.valueOf(a.getIdFactura()));

                Log.d("cliente: ", String.valueOf(a.getIdCliente()));

                Fragment fragment = new PDFFragment();
                Bundle bundle = new Bundle();
                bundle.putString("idFactura", new DecimalFormat("#").format(a.getIdFactura()));
                String IdEmpresa = String.valueOf(preferencesManager.getIntValue(mActivity,"IdEmpresa"));
                bundle.putString("informe", IdEmpresa);
                fragment.setArguments(bundle);

                mActivity.setFragment(fragment, 4);

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mActivity.setFragment(new PDFFragment(), 4);
                TableRow r = (TableRow) (v.getParent());

                int i = rows.lastIndexOf(r);

                String idFacturar = ((TextView)(r.getChildAt(0))).getText().toString();

                String cliente = ((TextView)(r.getChildAt(4))).getText().toString();

                ListDocsInfo a = lisDocList.get(i);
                Log.d("Factura: ", String.valueOf(a.getIdFactura()));

                Log.d("cliente: ", String.valueOf(a.getIdCliente()));

                String IdEmpresa = String.valueOf(preferencesManager.getIntValue(mActivity,"IdEmpresa"));

//
                reenviarApi(new DecimalFormat("#").format(a.getIdFactura()) , new DecimalFormat("#").format(a.getIdCliente()) );

            }
        });




        tableClients.addView(row);

        tableClients.requestLayout();

    }


    private void reenviarApi(String idFactura, String idCiente){

        String IdEmpresa = String.valueOf(preferencesManager.getIntValue(mActivity,"IdEmpresa"));

        connetionService.reenviarApi(IdEmpresa, idFactura,idCiente).enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                //Toast.makeText(rootView.getContext(), "send success", Toast.LENGTH_LONG).show();
                Object loginResponse = response.body();

                //Toast.makeText(mActivity, response.body().toString(), Toast.LENGTH_LONG).show();
                if(response.body() != null)
                if(!response.body().toString().equals("El sitio no esta configurado para enviar emails, puede que esté trabajando una versión de Test"))
                    new MaterialDialog.Builder(rootView.getContext())
                            .title("Mensaje")
                            .content("Se ha enviado satisfactoriamente")
                            .contentGravity(GravityEnum.CENTER)
                            .positiveText("Aceptar")
                            .onPositive((dialog, which) -> {
                            })
                            .show();
                else
                    new MaterialDialog.Builder(rootView.getContext())
                            .title("Error")
                            .content("El sitio no esta configurado para enviar emails, puede que esté trabajando una versión de Test")
                            .contentGravity(GravityEnum.CENTER)
                            .positiveText("Aceptar")
                            .onPositive((dialog, which) -> {
                            })
                            .show();

                utils.hideProgress();
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (ListDocActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
