package org.desperu.mynews.Views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import org.desperu.mynews.Models.NyTimesResults;
import org.desperu.mynews.R;
import org.desperu.mynews.Utils.MyNewsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NyTimesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_article_item_title) TextView textViewTitle;
    @BindView(R.id.fragment_article_item_image) ImageView imageView;
    @BindView(R.id.fragment_article_item_section_subsection) TextView textViewSection;
    @BindView(R.id.fragment_article_item_published_date) TextView textViewPublishedDate;

    public NyTimesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * Update text views and image view for each article.
     * @param nyTimesResults Information of the article.
     * @param glide Glide instance from the adapter to download the article's image.
     */
    public void updateWithArticle(NyTimesResults nyTimesResults, RequestManager glide) {
        if (nyTimesResults.getTitle() != null)
            this.textViewTitle.setText(nyTimesResults.getTitle());
        else this.textViewTitle.setText(nyTimesResults.getHeadline().getMain());

        if (nyTimesResults.getPublishedDate() != null && nyTimesResults.getPublishedDate().length() > 0)
            this.textViewPublishedDate.setText(MyNewsUtils.convertDate(nyTimesResults.getPublishedDate()));

        if (nyTimesResults.getSubsection() != null && nyTimesResults.getSubsection().length() > 0)
            this.textViewSection.setText(String.format(nyTimesResults.getSection() + " > " + nyTimesResults.getSubsection(), "%d"));
        else this.textViewSection.setText(nyTimesResults.getSection());

        if (nyTimesResults.getMultimedia() != null && !nyTimesResults.getMultimedia().isEmpty())
            glide.load(nyTimesResults.getMultimedia().get(0).getUrl()).into(imageView);
        else if (nyTimesResults.getMedia() != null) // TODO if MediaMetadatum is empty?
            glide.load(nyTimesResults.getMedia().get(0).getMediaMetadata().get(0).getUrl()).into(imageView);
    }
}