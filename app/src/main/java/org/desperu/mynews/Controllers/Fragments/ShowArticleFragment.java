package org.desperu.mynews.Controllers.Fragments;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.desperu.mynews.R;

import butterknife.BindView;

public class ShowArticleFragment extends BaseFragment {

    @BindView(R.id.fragment_show_article_web_view) WebView webView;
    @BindView(R.id.fragment_show_articles_swipe_container) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fragment_show_article_progress_bar) ProgressBar progressBar;

    private String articleUrl;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_show_article; }

    @Override
    protected void configureDesign() {
        this.configureAndShowWebView(articleUrl);
        this.configureSwipeRefreshLayout();
    }

    @Override
    protected void updateDesign() { }

    public ShowArticleFragment() { }

    /**
     * Configure and show Web View.
     * @param articleUrl The url's article.
     */
    private void configureAndShowWebView(String articleUrl) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(webView.getProgress()); // TODO to perform

        webView.loadUrl(articleUrl);
        webView.getSettings().setDisplayZoomControls(true);

        // Enable javascript.
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView or in a browser.
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * Configure swipe refresh layout.
     */
    private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.getViewTreeObserver().addOnScrollChangedListener(() -> swipeRefreshLayout.setEnabled(webView.getScrollY() == 0));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                configureAndShowWebView(articleUrl);
            }
        });
    }

    /**
     * Setter for url's article.
     * @param articleUrl The url's article.
     */
    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
}
