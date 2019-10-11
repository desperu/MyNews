package org.desperu.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.desperu.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleWebFragment extends Fragment {

    @BindView(R.id.fragment_article_web_view) WebView webView;
    @BindView(R.id.fragment_articles_web_view_swipe_container) SwipeRefreshLayout swipeRefreshLayout; // TODO a problem??

    private String articleUrl;

    public ArticleWebFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);
        this.configureAndShowWebView(articleUrl);

        return view;
    }

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

    //TODO needed?
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
