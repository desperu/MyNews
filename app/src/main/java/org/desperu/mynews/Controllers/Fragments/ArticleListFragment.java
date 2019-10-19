package org.desperu.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;

import org.desperu.mynews.Models.NyTimesAPI;
import org.desperu.mynews.Models.NyTimesResults;
import org.desperu.mynews.MyNewsTools;
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
    @State int fragmentKey;
    private String queryTerms;
    private String beginDate;
    private String endDate;
    private String sections;

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
        this.executeHttpRequestWithRetrofit(fragmentKey);
    }

    @Override
    protected void updateDesign() {  }

    // -----------------
    // CONSTRUCTORS
    // -----------------

    public ArticleListFragment() {}

    public ArticleListFragment(int fragmentKey, String queryTerms,
                               String beginDate, String endDate, String sections) {
        this.fragmentKey = fragmentKey;
        this.queryTerms = queryTerms;// TODO use bundles
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.sections = sections;
    }

    /**
     * Create a new instance of article list fragment.
     * @param position Number of the tab position, fragment key.
     * @return The new instance.
     */
    public static ArticleListFragment newInstance(int position) {
        ArticleListFragment articleListFragment = new ArticleListFragment();
        articleListFragment.fragmentKey = position;
        Icepick.saveInstanceState(articleListFragment, new Bundle());
        return articleListFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -----------------
    // ACTION
    // -----------------

    /**
     * Configure click on an item of recycler view.
     */
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

    /**
     * Configure callback to parent activity for manage click item.
     */
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

    /**
     * Configure recycler view.
     */
    private void configureRecyclerView(){
        this.nyTimesResults = new ArrayList<>();
        // Create adapter passing in the sample user data
        this.adapter = new NyTimesAdapter(this.nyTimesResults, Glide.with(this));
        // Attach the adapter to the recyclerView to populate items
        this.recyclerView.setAdapter(this.adapter);
        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Add separator between each item.
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.ic_divider));
        this.recyclerView.addItemDecoration(itemDecoration);

    }

    /**
     * Configure swipe to refresh layout.
     */
    private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit(fragmentKey);
            }
        });
    }

    /**
     * Configure progress bar.
     */
    private void configureProgressBar() { progressBar.setVisibility(View.VISIBLE); }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    /**
     * Execute Http request depending fragmentKey.
     * @param fragmentKey Fragment key.
     */
    private void executeHttpRequestWithRetrofit(int fragmentKey) {
        DisposableObserver<NyTimesAPI> disposableObserver = new DisposableObserver<NyTimesAPI>() {
            @Override
            public void onNext(NyTimesAPI nyTimesAPI) {
                if (nyTimesAPI.getResults() != null)
                    updateUI(nyTimesAPI.getResults());
                else if (nyTimesAPI.getResponse().getResults() != null) // TODO isEmpty ??
                    updateUI(nyTimesAPI.getResponse().getResults());
                else // TODO alert dialog
                    Toast.makeText(getContext(), "No result", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable e) { Toast.makeText(getContext(), "On error", Toast.LENGTH_LONG).show(); }

            @Override
            public void onComplete() { progressBar.setVisibility(View.GONE); }
        };

        switch (fragmentKey) {
            case MyNewsTools.FragmentsKeys.TOP_STORIES_FRAGMENT:
                this.disposable = NyTimesStreams.streamFetchNyTimesTopStories("home").subscribeWith(disposableObserver);
                break;
            case MyNewsTools.FragmentsKeys.MOST_POPULAR_FRAGMENT:
                this.disposable = NyTimesStreams.streamFetchNyTimesMostPopular().subscribeWith(disposableObserver);
                break;
            case MyNewsTools.FragmentsKeys.SCIENCES_FRAGMENT:
                this.disposable = NyTimesStreams.streamFetchNyTimesTopStories("science").subscribeWith(disposableObserver);
                break;
            case MyNewsTools.FragmentsKeys.SEARCH_RESULTS_FRAGMENT:
                this.disposable = NyTimesStreams.streamFetchNyTimesSearch(queryTerms, beginDate, endDate, sections).subscribeWith(disposableObserver);
                break;
            default:
                break;
        }
    }

    /**
     * Close disposable when destroy fragment.
     */
    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    /**
     * Update UI with articles list.
     * @param nyTimesResults Articles list.
     */
    private void updateUI(List<NyTimesResults> nyTimesResults){
        this.nyTimesResults.clear();
        this.nyTimesResults.addAll(nyTimesResults);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}