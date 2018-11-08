package com.prismaplus.fragments;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.prismaplus.BuildConfig;
import com.prismaplus.DrawerActivity;
import com.prismaplus.R;
import com.prismaplus.activities.BillingActivity;
import com.prismaplus.activities.ListDocActivity;
import com.prismaplus.activities.LoginActivity;
import com.prismaplus.herlpers.PreferencesManager;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.prismaplus.utils.Utils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.support.design.internal.NavigationMenu;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.MenuItem;
import android.view.SoundEffectConstants;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.prismaplus.BuildConfig;
import com.prismaplus.R;
import com.prismaplus.herlpers.PreferencesManager;
import com.prismaplus.services.ConnectionInterface;
import com.prismaplus.services.ConnetionService;
import com.prismaplus.utils.DownloadPDFAsyncTask;
import com.prismaplus.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

public class PDFFragment extends Fragment implements OnPageChangeListener {

    View rootView;
    private final int HOME = 16908332;
    private ListDocActivity mActivity;

    private PreferencesManager preferencesManager;
    private ConnectionInterface connetionService;
    Utils utils;

    /*HAY QUE CONTRIUR EL URL, PARA MOSTRARLOS EN DOWNLOAS*/
    private  String URL = "http://www.prismasolucionescr.com/PLUS/Index/Fact_Print?IdFactura=%s&Informe=%s&reimpresion=1";

    public String getIdFactura() {
        return IdFactura;
    }

    private String IdFactura  = "15959";
    String Informe  = "210/facturabase";
    PDFView pdfView;
    String lastPdfName = "";
    File lastPDF;
    public static final int MY_WRITE_LOCATION = 98;
    Response<ResponseBody> responseBody;

//    @BindView(R.id.webview)
//    WebView webView;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    @BindView(R.id.action_b)
    View actionB;

//    @BindView(R.id.action_a)
//    View actionA;

    @BindView(R.id.action_c)
    View actionC;

//    @BindView(R.id.pdfview)
//    PDFView pdfView;

    public PDFFragment(){
        utils  = new Utils();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        mActivity.getSupportActionBar().setTitle("PDF");
        preferencesManager = PreferencesManager.getInstance();
        connetionService = ConnetionService.obtenerServicio(preferencesManager.getStringValue(this.getActivity(),"url").equals("pruebas") ? utils.URL_PRUEBAS : utils.URL_PROD);


        try {
            mActivity.getSupportActionBar().setIcon(R.drawable.ic_prisma_big);
        }catch (Exception e){
            mActivity.getSupportActionBar().setIcon(R.drawable.ic_prisma);
        }




    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.);
//        ButterKnife.bind(this);
//        preferencesManager = PreferencesManager.getInstance();
//
//        final View actionB = findViewById(R.id.action_b);
//        actionB.setOnClickListener(view -> share());
//
//
//        final View actiona = findViewById(R.id.action_a);
//        actiona.setOnClickListener(view -> Toast.makeText(this.getActivity(), "Imprimiendo", Toast.LENGTH_LONG).show());
//
//
//        final View actionC = findViewById(R.id.action_c);
//        actionC.setOnClickListener(view -> saveInDownload());
//
//
//
//        pdfView = (PDFView) findViewById(R.id.pdfview);
//        connetionService = ConnetionService.obtenerServicio(preferencesManager.getStringValue(this,"url").equals("pruebas") ? utils.URL_PRUEBAS : utils.URL_PROD);
//
//
//        downloadPdfFromRetrofit();
//        // this.previewPdf();
//
//    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == MY_WRITE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            savePDFFromWEB();
        }
    }


    public void downloadPdfFromRetrofit(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (utils.verifyPermissions(this.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                savePDFFromWEB();
            }
            else
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_WRITE_LOCATION);
        } else {
            savePDFFromWEB();
        }
    }

    public void savePDFFromWEB(){
        connetionService.printFact("http://www.prismasolucionescr.com/PLUS/Index/Fact_Print", IdFactura, Informe, "1").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    responseBody = response;
                    DownloadPDFAsyncTask downloadFileAsyncTask = new DownloadPDFAsyncTask(PDFFragment.this);
                    assert response.body() != null;
                    downloadFileAsyncTask.execute(response.body().byteStream());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("PDFPRINT","mal");
            }
        });
    }

    public void previewPdf(){
//        webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + URL);
//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                webView.loadUrl("javascript:(function() { " +
//                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
//            }
//
//            public void onPageFinished(WebView view, String url) {
//                progressBar.setVisibility(View.GONE);
//                webView.loadUrl("javascript:(function() { " +
//                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
//            }
//        });
    }



    public void preview(String path){

        Log.d("PREVIEW", path);

        lastPDF  = new File(path);
        lastPdfName = path;
        pdfView.fromFile(new File(path)).defaultPage(1).enableSwipe(true).onPageChange(mActivity).load();

        Log.d("PREVIEW", "TRUE 2");

        progressBar.setVisibility(View.GONE);
    }

    public void share(){
        //Uri uri = Uri.fromFile(lastPDF);
        Uri uri = FileProvider.getUriForFile(this.getActivity(), BuildConfig.APPLICATION_ID + ".provider",lastPDF);

        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("application/pdf");
        share.putExtra(Intent.EXTRA_STREAM, uri);


        this.startActivity(share);
    }


    public void saveInDownload(){


        String url = String.format(URL, IdFactura, Informe);

        Log.d("URL: ", url);
        Uri uri = Uri.parse(String.format(URL, IdFactura, Informe));
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "factura _numero_" + IdFactura + ".pdf" );
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); // to notify when download is complete
        request.allowScanningByMediaScanner();
        DownloadManager manager = (DownloadManager) this.getActivity().getSystemService(DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PDFFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PDFFragment newInstance(String param1, String param2) {
        PDFFragment fragment = new PDFFragment();
        Bundle args = new Bundle();
//        args.putString("", param1);
//        args.putString("", param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_pdf, container, false);
        rootView = inflater.inflate(R.layout.fragment_pdf, container, false);
        ButterKnife.bind(this,rootView);

        preferencesManager = PreferencesManager.getInstance();

//        final View actionB = findViewById(R.id.action_b);
        actionB.setOnClickListener(view -> share());


//        final View actiona = findViewById(R.id.action_a);
        //actionA.setOnClickListener(view -> Toast.makeText(this.getActivity(), "Imprimiendo", Toast.LENGTH_LONG).show());


//        final View actionC = findViewById(R.id.action_c);
        actionC.setOnClickListener(view -> saveInDownload());

        pdfView = (PDFView) rootView.findViewById(R.id.pdfview);
        //connetionService = ConnetionService.obtenerServicio(preferencesManager.getStringValue(this.getActivity(),"url").equals("pruebas") ? utils.URL_PRUEBAS : utils.URL_PROD);

        IdFactura = this.getArguments().getString("idFactura");
        Informe = this.getArguments().getString("informe") + "/facturabaseapp";

        downloadPdfFromRetrofit();


        return rootView;
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
        mActivity = (ListDocActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Log.d("id", String.valueOf(item.getItemId()));
        switch (item.getItemId()) {
            case HOME:
                mActivity.getSupportActionBar().setTitle("LISTADO DE DOCUMENTOS");
                mActivity.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
