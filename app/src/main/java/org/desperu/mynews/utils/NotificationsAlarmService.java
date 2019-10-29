package org.desperu.mynews.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.desperu.mynews.controllers.activities.SearchResultsActivity;
import org.desperu.mynews.models.NyTimesAPI;
import org.desperu.mynews.MyNewsTools;
import org.desperu.mynews.R;

import java.util.Date;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static org.desperu.mynews.MyNewsTools.Constant.*;
import static org.desperu.mynews.utils.AppNotifications.*;

public class NotificationsAlarmService extends BroadcastReceiver {

    private static final int NOTIFICATION_ID = 1;

    private Disposable disposable;
    // Notification configuration.
    private String queryTerms;
    private String sections;
    // Must be set for retrofit request.
    private String beginDate;
    private String endDate;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.getNotificationConfigurationAndSetToFields(context);
        this.executeHttpRequestWithRetrofit(context);
    }

    /**
     * Create notification, and set on click.
     * @param context Context from this method is called.
     * @param numResults Number of results for the search.
     * @param unread Unread articles number.
     */
    private void createNotification(Context context, int numResults, int unread) {
        // Create notification.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_black_news_logo)
                .setTicker(context.getString(R.string.notification_ticker) + unread + context.getString(R.string.notification_subtext))
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(numResults + context.getString(R.string.notification_text))
                .setSubText(unread + context.getString(R.string.notification_subtext))
                .setLargeIcon(((BitmapDrawable)context.getResources().getDrawable(R.drawable.ic_my_news_logo)).getBitmap())
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setAutoCancel(true);

        // Create intent for notification click.
        Intent resultIntent = new Intent(context, SearchResultsActivity.class)
                .putExtra(SearchResultsActivity.QUERY_TERMS, queryTerms)
                .putExtra(SearchResultsActivity.BEGIN_DATE, beginDate)
                .putExtra(SearchResultsActivity.END_DATE, endDate)
                .putExtra(SearchResultsActivity.SECTIONS, sections);

        // Add parent to stack.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(SearchResultsActivity.class);

        // Adds the intent that starts the activity to the top of stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Get notification configuration saved and set to fields.
     * @param context Context from this method is called.
     */
    private void getNotificationConfigurationAndSetToFields(Context context) {
        this.queryTerms = MyNewsPrefs.getString(context, MyNewsTools.Keys.NOTIFICATION_QUERY_TERMS, null);
        this.sections = MyNewsPrefs.getString(context, MyNewsTools.Keys.NOTIFICATION_SECTIONS, null);
        this.beginDate = MyNewsUtils.dateToStringForNyTimes(MyNewsUtils.stringToDate(BEGIN_DATE_DEFAULT));
        this.endDate = MyNewsUtils.dateToStringForNyTimes(new Date());
    }

    /**
     * Not already read articles.
     * @param nyTimesAPI API response object.
     * @return Unread articles number
     */
    private int unreadArticlesNumber(Context context, NyTimesAPI nyTimesAPI) {
        int unread = 0;
        for (int i = 0; i < nyTimesAPI.getResponse().getResults().size(); i++) {
            if (MyNewsUtils.searchReadArticle(context, nyTimesAPI.getResponse().getResults().get(i).getUrl()) == -1)
                unread ++;
        }
        return unread;
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    /**
     * Execute Http request with retrofit, notification request.
     * @param context Context from this method is called.
     */
    private void executeHttpRequestWithRetrofit(Context context) {
        disposable = NyTimesStreams.streamFetchNyTimesSearch(queryTerms, beginDate, endDate, sections, SORT_BY)
                .subscribeWith(new DisposableObserver<NyTimesAPI>() {
            @Override
            public void onNext(NyTimesAPI nyTimesAPI) {
                createNotification(context, nyTimesAPI.getResponse().getResults().size(),
                        unreadArticlesNumber(context, nyTimesAPI));
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { disposeWhenRequestComplete(); }
        });
    }

    /**
     * Close disposable when request complete.
     */
    private void disposeWhenRequestComplete(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }
}