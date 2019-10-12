package org.desperu.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;

import org.desperu.mynews.Models.NyTimesAPI;
import org.desperu.mynews.Models.NyTimesResults;
import org.desperu.mynews.R;
import org.desperu.mynews.Utils.ItemClickSupport;
import org.desperu.mynews.Utils.NyTimesStreams;
import org.desperu.mynews.Views.NyTimesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import icepick.Icepick;
import icepick.State;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ArticleListFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.fragment_articles_list_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.fragment_articles_list_swipe_container) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fragment_article_list_progress_bar) ProgressBar progressBar;

    //FOR DATA
    private Disposable disposable;
    private List<NyTimesResults> nyTimesResults;
    private NyTimesAdapter adapter;
    @State int section;

    // Callback
    public interface OnClickedArticleListener {
        void onClickedArticle(String clickedArticle);
    }

    private ArticleListFragment.OnClickedArticleListener mCallback;


    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_articles_list; }

    @Override
    protected void configureDesign() {
        this.configureProgressBar();
        this.createCallbackToParentActivity();
        this.configureRecyclerView();
        this.configureSwipeRefreshLayout();
        this.configureOnClickRecyclerView();
        this.executeHttpRequestWithRetrofit(section);
    }

    @Override
    protected void updateDesign() {  }

    public ArticleListFragment() {}

    /**
     * Create a new instance of article list fragment.
     * @param section Number of the tab section.
     * @return The new instance.
     */
    public static ArticleListFragment newInstance(int section) { // TODO position?
        ArticleListFragment articleListFragment = new ArticleListFragment();
        articleListFragment.section = section;
        Icepick.saveInstanceState(articleListFragment, new Bundle());
        return articleListFragment;
    }

    private void configureProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -----------------
    // ACTION
    // -----------------

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_article_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        NyTimesResults nyTimesResults = adapter.getArticle(position);
                        mCallback.onClickedArticle(nyTimesResults.getUrl());
                    }
                });
    }

    // --------------
    // FRAGMENT SUPPORT
    // --------------

    private void createCallbackToParentActivity(){
        try {
            mCallback = (ArticleListFragment.OnClickedArticleListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()+ " must implement OnClickedArticleListener");
        }
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    private void configureRecyclerView(){
        this.nyTimesResults = new ArrayList<>();
        // Create adapter passing in the sample user data
        this.adapter = new NyTimesAdapter(this.nyTimesResults, Glide.with(this));
        // Attach the adapter to the recyclerView to populate items
        this.recyclerView.setAdapter(this.adapter);
        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit(section);
            }
        });
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    private void executeHttpRequestWithRetrofit(int section) {
        DisposableObserver<NyTimesAPI> disposableObserver = new DisposableObserver<NyTimesAPI>() {
            @Override
            public void onNext(NyTimesAPI nyTimesAPI) { updateUI(nyTimesAPI.getResults()); }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { progressBar.setVisibility(View.GONE); }
        };

        switch (section) {
            case 0:
                this.disposable = NyTimesStreams.streamFetchNYTTopStories("home").subscribeWith(disposableObserver);
                break;
            case 1:
                this.disposable = NyTimesStreams.streamFetchNYTMostPopular().subscribeWith(disposableObserver);
                break;
            case 2:
                this.disposable = NyTimesStreams.streamFetchNYTTopStories("science").subscribeWith(disposableObserver);
                break;
            default:
                break;
        }
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUI(List<NyTimesResults> nyTimesResults){
        this.nyTimesResults.clear();
        this.nyTimesResults.addAll(nyTimesResults);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}