package com.appclick_test.khokhlovart.appclicktest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

import com.appclick_test.khokhlovart.appclicktest.Results.AdInfo;
import java.io.IOException;

public class WebActivity extends AppCompatActivity {
    public static final int GET_AD_ID = 0;
    public static final String TEST_ID = "85950205030644900";
    WebView mWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        getAd();
    }

    private void showDialog(String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void setVisibleInMainGIThred(final int id, final int visible)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View tmp = findViewById(id);
                tmp.setVisibility(visible);
            }
        });
    }
    /********************************************************************************************************************
     ********************************  Loaders  *************************************************************************
     ********************************************************************************************************************/
    private void getAd() {
        getSupportLoaderManager().restartLoader(GET_AD_ID, null, new LoaderManager.LoaderCallbacks<AdInfo>() {
            @Override
            public Loader<AdInfo> onCreateLoader(int id, Bundle args) {

                return new AsyncTaskLoader<AdInfo>(getApplicationContext()) {
                    @Override

                    public AdInfo loadInBackground() {
                        try {
                            setVisibleInMainGIThred(R.id.progressBar, View.VISIBLE);
                            AdInfo res = ((App) getApplication()).getApi().getAd(TEST_ID).execute().body();
                            return res;
                        } catch (IOException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<AdInfo> loader, AdInfo res) {
                if ((res == null)){
                    showDialog(getString(R.string.some_problems));
                }
                else if (!res.status.equals(getString(R.string.result_ok))) {
                    showDialog(res.message);
                } else {
                    mWebView.loadUrl(res.url);
                    setVisibleInMainGIThred(R.id.progressBar, View.GONE);
                }
            }

            @Override
            public void onLoaderReset(Loader<AdInfo> loader) {

            }
        }).forceLoad();
    }
}
