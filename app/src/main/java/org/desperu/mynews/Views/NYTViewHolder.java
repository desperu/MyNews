package org.desperu.mynews.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import org.desperu.mynews.Models.NYTTopStories.TopStoriesResult;
import org.desperu.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NYTViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_article_item_title) TextView textViewTitle;
    @BindView(R.id.fragment_article_item_abstract) TextView texViewAbstract;
    @BindView(R.id.fragment_article_item_image) ImageView imageView;
    @BindView(R.id.fragment_article_item_section_subsection) TextView textViewSection;

    public NYTViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithArticle(TopStoriesResult topStoriesResult, RequestManager glide) {
        this.textViewTitle.setText(topStoriesResult.getTitle());
        this.texViewAbstract.setText(topStoriesResult.getAbstract());
        this.textViewSection.setText(topStoriesResult.getSection() + ">" + topStoriesResult.getSubsection());
        if (!topStoriesResult.getMultimedia().isEmpty())
        glide.load(topStoriesResult.getMultimedia().get(0).getUrl()).into(imageView);
    }
}