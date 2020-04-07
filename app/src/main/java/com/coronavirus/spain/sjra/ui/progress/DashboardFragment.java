package com.coronavirus.spain.sjra.ui.progress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.coronavirus.spain.sjra.R;
import com.coronavirus.spain.sjra.rest.CovidDataService;
import com.coronavirus.spain.sjra.rest.RetrofitClientInstance;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private ProgressBar progressBar;

    private View emptyTextLayout;

    private WebView mWebView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        progressBar = root.findViewById(R.id.dasboardProgressBar);
        emptyTextLayout = root.findViewById(R.id.dashboardEmptyTextLayout);

        mWebView = (WebView) root.findViewById(R.id.webView);

        showProgress(true);

        CovidDataService covidDataService = RetrofitClientInstance.getRetrofitInstance().create(CovidDataService.class);
        Call<ResponseBody> call = covidDataService.getIframe();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    showProgress(false);
                    try {
                        String url = response.body().string();

                        // text/html
                        String mimeType = response.body().contentType().toString().split(";")[0].trim();
                        loadWebView(url, mimeType);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showProgress(false);
                emptyTextLayout.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

    private void showProgress(final boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mWebView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private void loadWebView(String url, String mimeType){
        mWebView.loadData(url, mimeType, null);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                showProgress(false);
            }
        });
    }
}
