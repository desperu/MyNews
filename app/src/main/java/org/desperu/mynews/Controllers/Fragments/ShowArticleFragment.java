package org.desperu.mynews.Controllers.Fragments;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.desperu.mynews.R;

import butterknife.BindView;

public class ShowArticleFragment extends BaseFragment {

    @BindView(R.id.fragment_show_article_web_view) WebView webView;
    @BindView(R.id.fragment_show_articles_swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    private String articleUrl;

    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_show_article; }

    @Override
    protected void configureDesign() {
        this.configureAndShowWebView(articleUrl);
    }

    @Override
    protected void updateDesign() { }

    public ShowArticleFragment() { }

    /**
     * Configure and show Web View.
     * @param articleUrl The url's article.
     */
    private void configureAndShowWebView(String articleUrl) {
        webView.loadUrl(articleUrl);

        // Enable javascript.
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView or in a browser.
        webView.setWebViewClient(new WebViewClient());
    }

    /**
     * Setter for url's article.
     * @param articleUrl The url's article.
     */
    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
}
