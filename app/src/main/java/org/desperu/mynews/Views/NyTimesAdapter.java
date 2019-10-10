package org.desperu.mynews.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import org.desperu.mynews.Models.NyTimesResults;
import org.desperu.mynews.R;

import java.util.List;

public class NyTimesAdapter extends RecyclerView.Adapter<NyTimesViewHolder> {

    // FOR DATA
    private List<NyTimesResults> nyTimesResults;
    private RequestManager glide;

    // CONSTRUCTOR
    public NyTimesAdapter(List<NyTimesResults> nyTimesResults, RequestManager glide) {
        this.nyTimesResults = nyTimesResults;
        this.glide = glide;
    }

    @Override
    public NyTimesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_article_item, parent, false);

        return new NyTimesViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A NEW YORK TIMES ARTICLE
    @Override
    public void onBindViewHolder(NyTimesViewHolder viewHolder, int position) {
        viewHolder.updateWithArticle(this.nyTimesResults.get(position), this.glide);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.nyTimesResults.size();
    }

    public NyTimesResults getArticle(int position) { return this.nyTimesResults.get(position); }
}