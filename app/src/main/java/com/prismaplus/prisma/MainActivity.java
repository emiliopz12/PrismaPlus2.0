package com.prismaplus.prisma;

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
import com.prismaplus.DrawerActivity;
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

public class MainActivity extends Activity /*implements OnPageChangeListener*/{

//    private PreferencesManager preferencesManager;
//    private ConnectionInterface connetionService;
//    private Utils utils;
//
//    /*HAY QUE CONTRIUR EL URL, PARA MOSTRARLOS EN DOWNLOAS*/
//    private  String URL = "http://www.prismasolucionescr.com/PLUS/Index/Fact_Print?IdFactura=15959&Informe=210/facturabase&reimpresion=1";
//
//    public String getIdFactura() {
//        return IdFactura;
//    }
//
//    private String IdFactura  = "15959";
//    String Informe  = "210/facturabase";
//    PDFView pdfView;
//    String lastPdfName = "";
//    File lastPDF;
//    public static final int MY_WRITE_LOCATION = 98;
//    Response<ResponseBody> responseBody;
////
////   @BindView(R.id.webview)
////    WebView webView;
//
//    @BindView(R.id.progressbar)
//    ProgressBar progressBar;
//
//    public MainActivity(){
//        utils  = new Utils();
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        preferencesManager = PreferencesManager.getInstance();
//
//        final View actionB = findViewById(R.id.action_b);
//        actionB.setOnClickListener(view -> share());
//
//
//        final View actiona = findViewById(R.id.action_a);
//        actiona.setOnClickListener(view -> Toast.makeText(MainActivity.this, "Imprimiendo", Toast.LENGTH_LONG).show());
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
//        Intent intent = getIntent();
//
//        IdFactura = intent.getStringExtra("idFactura");
//        Informe = intent.getStringExtra("informe") + "/facturabase";
//
//
//        downloadPdfFromRetrofit();
//       // this.previewPdf();
//
//    }
//
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//     if(requestCode == MY_WRITE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//         savePDFFromWEB();
//     }
//    }
//
//
//    public void downloadPdfFromRetrofit(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (utils.verifyPermissions(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
//            {
//                savePDFFromWEB();
//            }
//            else
//                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_WRITE_LOCATION);
//        } else {
//            savePDFFromWEB();
//        }
//    }
//
//    public void savePDFFromWEB(){
//        connetionService.printFact("http://www.prismasolucionescr.com/PLUS/Index/Fact_Print",IdFactura,Informe, "1").enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if(response.isSuccessful()){
//                    responseBody = response;
//                    DownloadPDFAsyncTask downloadFileAsyncTask = new DownloadPDFAsyncTask(MainActivity.this);
//                    assert response.body() != null;
//                    downloadFileAsyncTask.execute(response.body().byteStream());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d("PDFPRINT","mal");
//            }
//        });
//    }
//
//   public void previewPdf(){
////        webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + URL);
////        webView.setWebViewClient(new WebViewClient() {
////
////            @Override
////            public void onPageStarted(WebView view, String url, Bitmap favicon) {
////                super.onPageStarted(view, url, favicon);
////                webView.loadUrl("javascript:(function() { " +
////                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
////            }
////
////            public void onPageFinished(WebView view, String url) {
////                progressBar.setVisibility(View.GONE);
////                webView.loadUrl("javascript:(function() { " +
////                        "document.querySelector('[role=\"toolbar\"]').remove();})()");
////            }
////        });
//    }
//
//    @Override
//    public void onPageChanged(int page, int pageCount) {
//
//    }
//
//    public void preview(String path){
//        lastPDF  = new File(path);
//        lastPdfName = path;
//        pdfView.fromFile(new File(path)).defaultPage(1).enableSwipe(true).onPageChange(this).load();
//
//        progressBar.setVisibility(View.GONE);
//    }
//
//
//   public void share(){
//        //Uri uri = Uri.fromFile(lastPDF);
//        Uri uri = FileProvider.getUriForFile(MainActivity.this, BuildConfig.APPLICATION_ID + ".provider",lastPDF);
//
//        Intent share = new Intent();
//        share.setAction(Intent.ACTION_SEND);
//        share.setType("application/pdf");
//        share.putExtra(Intent.EXTRA_STREAM, uri);
//
//
//        this.startActivity(share);
//    }
//
//
//    public void saveInDownload(){
//        Uri uri = Uri.parse(URL);
//        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "factura _numero_" + IdFactura + ".pdf" );
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); // to notify when download is complete
//        request.allowScanningByMediaScanner();
//        DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
//        manager.enqueue(request);
//
//    }

}
