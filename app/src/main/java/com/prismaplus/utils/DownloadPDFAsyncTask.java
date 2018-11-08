package com.prismaplus.utils;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.prismaplus.DrawerActivity;
import com.prismaplus.fragments.PDFFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

public class DownloadPDFAsyncTask extends AsyncTask<InputStream, Void, Boolean> {

    PDFFragment mainActivity;
    final String appDirectoryName = "PrismaPlus";
    final File imageRoot = new File(Environment.getExternalStorageDirectory(), appDirectoryName);
    String filename = "image.jpg";
    private final String TAG = "PREVIEW";
    Date date = new Date();
    File mediaStorageDir;
    public DownloadPDFAsyncTask( PDFFragment mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected Boolean doInBackground(InputStream... params) {
        InputStream inputStream = params[0];


        mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "PrismaPlus");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("App", "failed to create directory");
            }
        }

        date = new Date();
        filename = "factura_numero_" +  mainActivity.getIdFactura()  + ".pdf";
        File file = new File(imageRoot, filename);
        OutputStream output = null;
        try {
            try {
                output = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            byte[] buffer = new byte[1024]; // or other buffer size
            int read;

            Log.d(TAG, "Attempting to write to: " + imageRoot + "/" + filename);
            while ((read = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, read);
                Log.v(TAG, "Writing to buffer to output stream.");
            }
            Log.d(TAG, "Flushing output stream.");
            output.flush();
            Log.d(TAG, "Output flushed.");
        } catch (IOException e) {
            Log.e(TAG, "IO Exception: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (output != null) {
                    output.close();
                    Log.d(TAG, "Output stream closed sucessfully.");
                }
                else{
                    Log.d(TAG, "Output stream is null");
                }
            } catch (IOException e){
                Log.e(TAG, "Couldn't close output stream: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        Log.d(TAG, "Download success: " + result);
        this.mainActivity.preview(mediaStorageDir.getAbsolutePath()+ "/" +filename);
        // TODO: show a snackbar or a toast
    }
}

