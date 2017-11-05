package com.ethanhua.eyepetizer.module;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ethanhua.eyepetizer.BaseActivity;
import com.ethanhua.eyepetizer.R;
import com.ethanhua.eyepetizer.databinding.ActivityWebBinding;

/**
 * Created by ethanhua on 2017/10/30.
 *
 * input data must include title and url
 *
 */

public class WebActivity extends BaseActivity {
    private final String PARAMS_TITLE = "title";
    private final String PARAMS_URL = "url";
    private ActivityWebBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        initView();
        getInputData();
    }

    private void initView() {
        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        binding.webView.setWebChromeClient(new BaseWebChromeClient());
        binding.toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_white_36dp);
        binding.toolbar.setNavigationOnClickListener(v -> finish());
    }

    /**
     * input data include {title,url}
     */
    private void getInputData() {
        Uri uri = getIntent().getData();
        String title = uri.getQueryParameter(PARAMS_TITLE);
        String url = uri.getQueryParameter(PARAMS_URL);
        binding.webView.loadUrl(url);
        binding.tvTitle.setText(title);
    }

    public class BaseWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int progress) {
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.progressBar.setProgress(progress);
            if (progress >= 100) {
                binding.progressBar.setVisibility(View.GONE);
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (binding.webView != null) {
            binding.webView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (binding.webView != null) {
            binding.webView.onResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (binding.webView != null) {
            binding.webView.stopLoading();
        }
    }

    @Override
    protected void onDestroy() {
        if (binding.webView != null) {
            ViewParent parent = binding.webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(binding.webView);
            }

            binding.webView.removeAllViews();
            binding.webView.destroy();
        }
        super.onDestroy();
    }

}
