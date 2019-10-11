package org.desperu.mynews.Controllers.Fragments;

import android.view.View;

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
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class MostPopularFragment extends BaseFragment{

    // FOR DESIGN
    @BindView(R.id.fragment_articles_list_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.fragment_articles_list_swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    //FOR DATA
    private Disposable disposable;
    private List<NyTimesResults> nyTimesResults;
    private NyTimesAdapter adapter;

    // Callback
    public interface OnClickedArticleListener {
        void onClickedArticle(String clickedArticle);
    }

    private MostPopularFragment.OnClickedArticleListener mCallback; // TODO WeakReference??


    // --------------
    // BASE METHODS
    // --------------

    @Override
    protected BaseFragment getNewInstance() { return MostPopularFragment.newInstance(); }

    @Override
    protected int getFragmentLayout() { return R.layout.fragment_articles_list; }

    @Override
    protected void configureDesign() {
        this.createCallbackToParentActivity();
        this.configureRecyclerView();
        this.configureSwipeRefreshLayout();
        this.configureOnClickRecyclerView();
        this.executeHttpRequestWithRetrofit();
    }

    @Override
    protected void updateDesign() { }

    public static MostPopularFragment newInstance() { return new MostPopularFragment(); }

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
            mCallback = (OnClickedArticleListener) getActivity();
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
                executeHttpRequestWithRetrofit();
            }
        });
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    private void executeHttpRequestWithRetrofit(){
        this.disposable = NyTimesStreams.streamFetchNYTMostPopular().subscribeWith(new DisposableObserver<NyTimesAPI>() {
            @Override
            public void onNext(NyTimesAPI nyTimesAPI) {
                updateUI(nyTimesAPI.getResults());
            } //TODO add a progressBar

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }
        });
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