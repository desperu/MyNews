package org.desperu.mynews.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import org.desperu.mynews.Models.NyTimesResults;
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

    public void updateWithArticle(NyTimesResults nyTimesResults, RequestManager glide) {
        this.textViewTitle.setText(nyTimesResults.getTitle());
        this.texViewAbstract.setText(nyTimesResults.getAbstract());
        if (nyTimesResults.getSubsection() != null)// || nyTimesResults.getSubsection() != " ")
            this.textViewSection.setText(String.format(nyTimesResults.getSection() + " > " + nyTimesResults.getSubsection(), "%d"));
        else this.textViewSection.setText(nyTimesResults.getSection());
//        this.textViewSection.setText(topStoriesResult.getSection() + " > " + topStoriesResult.getSubsection()); //TODO to check
        if (nyTimesResults.getMultimedia() != null) {
            if (!nyTimesResults.getMultimedia().isEmpty())
                glide.load(nyTimesResults.getMultimedia().get(0).getUrl()).into(imageView);
        }
        else if (nyTimesResults.getMedia() != null)
            glide.load(nyTimesResults.getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(imageView);
    }
}