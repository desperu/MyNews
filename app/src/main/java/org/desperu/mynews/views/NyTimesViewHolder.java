package org.desperu.mynews.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;

import org.desperu.mynews.models.NyTimesResults;
import org.desperu.mynews.R;
import org.desperu.mynews.utils.MyNewsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.desperu.mynews.MyNewsTools.Constant.*;

public class NyTimesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_article_item_linear_layout_root) LinearLayout linearLayout;
    @BindView(R.id.fragment_article_item_title) TextView textViewTitle;
    @BindView(R.id.fragment_article_item_image) ImageView imageView;
    @BindView(R.id.fragment_article_item_section_subsection) TextView textViewSection;
    @BindView(R.id.fragment_article_item_published_date) TextView textViewPublishedDate;

    private NyTimesResults nyTimesResults;

    public NyTimesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * Update text views and image view for each article.
     * @param nyTimesResults Information of the article.
     * @param glide Glide instance from the adapter to download the article's image.
     * @param context Context from this method is called.
     */
    public void updateWithArticle(NyTimesResults nyTimesResults, RequestManager glide, Context context) {
        this.nyTimesResults = nyTimesResults;
        // For history.
        this.alreadyReadArticles(context);
        // For title.
        this.textViewTitle.setText(this.getTitleArticle());
        //For published date.
        this.textViewPublishedDate.setText(this.getPublishedDateArticle());
        // For section and subsection.
        this.textViewSection.setText(this.getSectionArticle());
        // For image.
        glide.load(getImageUrlArticle()).into(imageView);
    }

    /**
     * Change font color if article is in history.
     * @param context Context from this method is called.
     */
    private void alreadyReadArticles(Context context) {
        if (MyNewsUtils.searchReadArticle(context, nyTimesResults.getUrl()) != -1)
            linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorGreyLight));
        else linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
    }

    /**
     * Get title for each article with the good json localisation.
     * @return String title article.
     */
    private String getTitleArticle() {
        if (nyTimesResults.getTitle() != null)
            return nyTimesResults.getTitle();
        else return nyTimesResults.getHeadline().getMain();
    }

    /**
     * Get published date for each article with the good json localisation.
     * @return String published date.
     */
    private String getPublishedDateArticle() {
        if (nyTimesResults.getPublishedDate() != null && nyTimesResults.getPublishedDate().length() > 0)
            return MyNewsUtils.convertDate(nyTimesResults.getPublishedDate());
        return "";
    }

    /**
     * Get section and subsection for each article with the good localisation in json.
     * @return String section and subsection article.
     */
    private String getSectionArticle() {
        if (nyTimesResults.getSubsection() != null && nyTimesResults.getSubsection().length() > 0)
            return String.format(nyTimesResults.getSection() + " > " + nyTimesResults.getSubsection(), "%d");
        else return nyTimesResults.getSection();
    }

    /**
     * Get image url for each article, depending localisation in json.
     * @return String image url.
     */
    private String getImageUrlArticle() {
        String imageUrl = "";
        if (nyTimesResults.getMultimedia() != null && !nyTimesResults.getMultimedia().isEmpty()) {
            if (nyTimesResults.getMultimedia().get(0).getCropName() != null) {

                // For search result.
                for (int i = 0; i < nyTimesResults.getMultimedia().size(); i++) {
                    if (nyTimesResults.getMultimedia().get(i).getCropName().equals("thumbStandard"))
                        imageUrl = nyTimesImageUrl + nyTimesResults.getMultimedia().get(i).getUrl();
                }

                // For Top Stories.
            } else imageUrl = nyTimesResults.getMultimedia().get(0).getUrl();

            // For Most Popular.
        } else if (nyTimesResults.getMedia() != null && !nyTimesResults.getMedia().get(0).getMediaMetadata().isEmpty())
            imageUrl = nyTimesResults.getMedia().get(0).getMediaMetadata().get(0).getUrl();

        return imageUrl;
    }
}