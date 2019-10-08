package org.desperu.mynews.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import org.desperu.mynews.Models.NYTTopStories.TopStoriesResult;
import org.desperu.mynews.R;

import java.util.List;

public class NYTAdapter extends RecyclerView.Adapter<NYTViewHolder> {

    // FOR DATA
    private List<TopStoriesResult> topStoriesResults;
    private RequestManager glide;

    // CONSTRUCTOR
    public NYTAdapter(List<TopStoriesResult> topStoriesResults, RequestManager glide) {
        this.topStoriesResults = topStoriesResults;
        this.glide = glide;
    }

    @Override
    public NYTViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_article_item, parent, false);

        return new NYTViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A NEW YORK TIMES ARTICLE
    @Override
    public void onBindViewHolder(NYTViewHolder viewHolder, int position) {
        viewHolder.updateWithArticle(this.topStoriesResults.get(position), this.glide);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.topStoriesResults.size();
    }

    public TopStoriesResult getArticle(int position) { return this.topStoriesResults.get(position); }
}