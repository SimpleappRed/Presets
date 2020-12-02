package com.wiadevelopers.presets.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.wiadevelopers.presets.Adapter.AdapterAllPresets;
import com.wiadevelopers.presets.Adapter.AdapterDetailPresets;
import com.wiadevelopers.presets.Class.MySingleton;
import com.wiadevelopers.presets.Model.AllPresetsModel;
import com.wiadevelopers.presets.R;
import com.wiadevelopers.presets.callback.SetAdListener;
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
    private RewardedAd rewardedAd;
    private static final String TAG = "ActivityDetailsCtegory";




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
        adapter = new AdapterDetailPresets(ActivityDetailsCtegory.this, new SetAdListener() {
            @Override
            public void onClick(View view) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadAd(view);
                    }
                }, 5000);

                showAd(view);

            }
        }, new SetOnClickListener() {
            @Override
            public void onClick(String url) {
                url_1 = url;
                Log.i("ttttt", "first");

                    //checkPermission();


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
                        .setMessage("You need Storage Permission to download DNG")
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
                new AlertDialog.Builder(ActivityDetailsCtegory.this)
                        .setTitle("Permission")
                        .setCancelable(false)
                        .setMessage("You need Storage Permission to download DNG")
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


               /* ActivityCompat.requestPermissions(ActivityDetailsCtegory.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_MULTIPLE_REQUEST);*/


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

    public void loadAd(View view) {
        this.rewardedAd = new RewardedAd(this, "ca-app-pub-3940256099942544/5224354917");
        RewardedAdLoadCallback callback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdFailedToLoad(LoadAdError loadAdError) {
                super.onRewardedAdFailedToLoad(loadAdError);
                Log.i(TAG, "onRewardedAdFailedToLoad");

            }

            @Override
            public void onRewardedAdLoaded() {
                super.onRewardedAdLoaded();
                Log.i(TAG, "onRewardedAdLoaded");

            }
        };
        this.rewardedAd.loadAd(new AdRequest.Builder().build(), callback);

    }

    public void showAd(View view) {
        try {
            if (this.rewardedAd.isLoaded()) {
                RewardedAdCallback callback = new RewardedAdCallback() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        Log.i(TAG, "onUserEarnedReward");
                        checkPermission();
                    }

                    @Override
                    public void onRewardedAdOpened() {
                        super.onRewardedAdOpened();
                        Log.i(TAG, "onRewardedAdOpened");
                    }

                    @Override
                    public void onRewardedAdClosed() {
                        super.onRewardedAdClosed();
                        Log.i(TAG, "onRewardedAdClosed");
                    }

                    @Override
                    public void onRewardedAdFailedToShow(AdError adError) {
                        super.onRewardedAdFailedToShow(adError);
                        Log.i(TAG, "onRewardedAdFailedToShow");
                    }

                };
                this.rewardedAd.show(this, callback);


            } else {
                Log.i(TAG, "ad not loaded");
            }

        } catch (Exception e) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View v2 = layoutInflater.inflate(R.layout.toastcustom, (ViewGroup) findViewById(R.id.lnr));
            TextView t;
            t = v2.findViewById(R.id.toast_description);
            t.setText(R.string.load_toast_decription);
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            //toast.setGravity(Gravity.NO_GRAVITY, 0, 160);
            toast.setView(v2);
            toast.show();

        }


    }

}