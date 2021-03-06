package org.desperu.mynews.controllers.fragments;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.desperu.mynews.controllers.activities.ShowArticleActivity;
import org.desperu.mynews.R;
import org.desperu.mynews.views.LollipopFixedWebView;

import butterknife.BindView;
import icepick.State;

public class ShowArticleFragment extends BaseFragment {

    @BindView(R.id.fragment_show_article_web_view) LollipopFixedWebView webView;
    @BindView(R.id.fragment_show_articles_swipe_container) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fragment_show_article_progress_bar) ProgressBar progressBar;

    @State String articleUrl;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_show_article; }

    @Override
    protected void configureDesign() {
        this.setArticleUrl();
        this.configureAndShowWebViewWithProgressBar();
        this.configureSwipeRefreshLayout();
    }

    public ShowArticleFragment() { }

    // --------------
    // CONFIGURATION
    // --------------

    /**
     * Set articleUrl with bundle argument if not null.
     */
    private void setArticleUrl() {
        if (this.getArguments() != null && articleUrl == null)
            this.articleUrl = this.getArguments().getString(ShowArticleActivity.ARTICLE_URL, null);
    }

    /**
     * Configure and show Web View with Progress Bar.
     */
    private void configureAndShowWebViewWithProgressBar() {

        // Set progress bar with page loading.
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setMax(100);
                progressBar.setProgress(newProgress);
            }
        });

        // Force links and redirects to open in the WebView.
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                articleUrl = url;
                progressBar.setProgress(0);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        webView.loadUrl(articleUrl);
    }

    /**
     * Configure swipe refresh layout.
     */
    private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.getViewTreeObserver().addOnScrollChangedListener(() ->
                swipeRefreshLayout.setEnabled(webView.getScrollY() == 0));
        swipeRefreshLayout.setOnRefreshListener(this::configureAndShowWebViewWithProgressBar);
    }

    /**
     * Getter for article url.
     * @return String article url.
     */
    public String getArticleUrl() {
        return this.articleUrl;
    }
}