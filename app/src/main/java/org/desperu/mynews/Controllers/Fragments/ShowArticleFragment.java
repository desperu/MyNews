package org.desperu.mynews.Controllers.Fragments;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
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
        this.configureAndShowWebViewWithProgressBar(articleUrl);
        this.configureSwipeRefreshLayout();
    }

    @Override
    protected void updateDesign() { }

    public ShowArticleFragment(String articleUrl) { this.articleUrl = articleUrl; } // TODO use bundles

    /**
     * Configure and show Web View with Progress Bar.
     * @param articleUrl The url's article.
     */
    private void configureAndShowWebViewWithProgressBar(String articleUrl) {

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
        swipeRefreshLayout.setOnRefreshListener(() -> configureAndShowWebViewWithProgressBar(articleUrl));
    }
}
