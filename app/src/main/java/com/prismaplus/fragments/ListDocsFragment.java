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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.activities.ClientsActivity;
import com.prismaplus.activities.ListDocActivity;
import com.prismaplus.entities.ListDocsInfo;
import com.prismaplus.entities.ProductInfo;
import com.prismaplus.herlpers.PreferencesManager;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.prismaplus.utils.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

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
        }
    }




    public void pupulateListDocs() {

        String IdEmpresa = String.valueOf(preferencesManager.getIntValue(getActivity(),"IdEmpresa"));

        utils.showProgess(getActivity(),"Cargando");

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
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerowproductlist, null);
        else
            row = (TableRow)LayoutInflater.from(getActivity()).inflate(R.layout.tablerowproductlistwhite, null);

        ((TextView)row.findViewById(R.id.codigo)).setText(client.getCodigoArticulo());
        ((TextView)row.findViewById(R.id.desc)).setText(client.getDescripcion());

        rows.add(row);



        tableClients.addView(row);

        tableClients.requestLayout();

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
