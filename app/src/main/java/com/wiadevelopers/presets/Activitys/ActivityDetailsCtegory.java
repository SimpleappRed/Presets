package com.wiadevelopers.presets.Activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.wiadevelopers.presets.Adapter.AdapterAllPresets;
import com.wiadevelopers.presets.Adapter.AdapterDetailPresets;
import com.wiadevelopers.presets.Class.MySingleton;
import com.wiadevelopers.presets.Model.AllPresetsModel;
import com.wiadevelopers.presets.R;
import com.wiadevelopers.presets.callback.SetOnClickListener;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ActivityDetailsCtegory extends AppCompatActivity {
    private static final int PERMISSIONS_MULTIPLE_REQUEST = 1001;
    private SetOnClickListener setOnClickListener;
    RecyclerView recycle_detail_category;
    AdapterDetailPresets adapter;
    public static AllPresetsModel presetModel;
    String url_1 = "";
    TextView category_name, category_description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_ctegory);


        getIntentMethod();


        recycle_detail_category = findViewById(R.id.recycle_detail_category);
        recycle_detail_category.setLayoutManager(new LinearLayoutManager(ActivityDetailsCtegory.this));
        recycle_detail_category.setHasFixedSize(true);
        recycle_detail_category.setItemViewCacheSize(20);
        recycle_detail_category.setAlwaysDrawnWithCacheEnabled(true);
        recycle_detail_category.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        adapter = new AdapterDetailPresets(ActivityDetailsCtegory.this, new SetOnClickListener() {


            @Override
            public void onClick(String url) {
                url_1 = url;
                Log.i("ttttt", "first");
                checkPermission();
            }
        }
                , presetModel.getPresetsDNG_Format()
                , presetModel.getPresetsAfter()
                , presetModel.getPresetsBefore()
                , presetModel.getPresetsDetailsname()
                , presetModel.getPresetsDetailsname());
        recycle_detail_category.setAdapter(adapter);

        setCategoryNameAndDescription();


    }

    private void setCategoryNameAndDescription() {
        category_name = findViewById(R.id.category_name);
        category_description = findViewById(R.id.category_description);
        category_name.setText(presetModel.getPresetsDetailsname());
        //category_description.setText(presetModel.getCategoryDescription());

    }


    public void getIntentMethod() {

        presetModel = (AllPresetsModel) getIntent().getSerializableExtra("model");


    }


    private void checkPermission() {
        if (
                ContextCompat.checkSelfPermission(ActivityDetailsCtegory.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {


            if (
                    ActivityCompat.shouldShowRequestPermissionRationale
                            (ActivityDetailsCtegory.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {


                new AlertDialog.Builder(ActivityDetailsCtegory.this)
                        .setTitle("Permission")
                        .setMessage("You must have permission to download this")
                        .setPositiveButton("OK", (dialogInterface, i) -> ActivityCompat.requestPermissions(ActivityDetailsCtegory.this,
                                new String[]{Manifest.permission
                                        .WRITE_EXTERNAL_STORAGE},
                                PERMISSIONS_MULTIPLE_REQUEST))
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .create()
                        .show();
                ;


            } else {
                ActivityCompat.requestPermissions(ActivityDetailsCtegory.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_MULTIPLE_REQUEST);


            }
        } else {
            createFolder();
            // write your logic code if permission already granted
        }
    }


    private void createFolder() {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "wiadeveloper");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
            new downloadFile().execute(url_1, "");

        }
        if (success) {
            new downloadFile().execute(url_1, "");

        } else {
            Toast.makeText(this, "Problem creating folder",
                    Toast.LENGTH_LONG).show(); // Do something else on failure
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_MULTIPLE_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    createFolder();

                } else {


                    Log.i("ttttt", "n");
                    checkPermission();
                }
                break;
        }
    }

    public class downloadFile extends AsyncTask<String, String, String> {


        private final String fileType = "imageDownload.dng";
        private ProgressDialog videoProgressDialog;
        private String folder;

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         */
        @Override

        protected void onPreExecute() {
            super.onPreExecute();


            //  File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Quiztion/quiztion.file");
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "wiadeveloper/" + fileType);

            try {

                if (file.exists())
                    file.delete();

            } catch (Exception e) {
                e.printStackTrace();
            }

            this.videoProgressDialog = new ProgressDialog(ActivityDetailsCtegory.this, R.style.AppTheme_Dark_Dialog);
            this.videoProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            this.videoProgressDialog.setTitle("Downloading...");
            this.videoProgressDialog.setCancelable(false);
            this.videoProgressDialog.show();


        }

        /**
         * Downloading file in background thread
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                // getting file length
                int lengthOfFile = connection.getContentLength();

                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                //  folder = Environment.getExternalStorageDirectory() + File.separator + "Quiztion/";
                //  android.content.Context.getExternalFilesDir():
                folder = Environment.getExternalStorageDirectory() + File.separator + "wiadeveloper/";

                File directory = new File(folder);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                OutputStream output = new FileOutputStream(folder + fileType);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    //  Log.d("Video", "Progress: " + (int) ((total * 100) / lengthOfFile));

                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();
//                sharedPreferences.edit().putString(Const.MAIN_MATCH_VIDEO_NAME, f_url[1]).apply();
                return "";

            } catch (Exception e) {
                //     Log.e("Error: ", e.getMessage());

                try {


                    File file = new File(folder + fileType);
                    if (file.exists()) {
                        file.delete();
                    }


                } catch (Exception e1) {
                    e1.printStackTrace();


                }
            }

            return "Something went wrong";
        }

        /**
         * Updating progress bar
         */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            this.videoProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }


        @Override
        protected void onPostExecute(String message) {
            // dismiss the dialog after the file was downloaded
            this.videoProgressDialog.dismiss();
            shareImage();

        }


    }


    private void shareImage() {


        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "wiadeveloper/imageDownload.dng");


        Uri uri = FileProvider.getUriForFile(this, "com.wiadevelopers.presets.fileprovider", file);
//        Intent intent = ShareCompat.IntentBuilder.from(this)
//                .setType("image/dng")
//                .setStream(uri)
//
//                .setChooserTitle("Choose bar")
//                .createChooserIntent()
//                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//intent.setPackage("com.adobe.lrmobile");
//        shareIntent.setPackage("com.adobe.lrmobile");

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/dng");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setPackage("com.adobe.lrmobile");

        shareIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);


        try {
            startActivity(shareIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "your app not install", Toast.LENGTH_SHORT).show();
        }


    }

}