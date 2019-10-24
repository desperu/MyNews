package org.desperu.mynews.Utils;

import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.desperu.mynews.Controllers.Activities.SearchResultsActivity;
import org.desperu.mynews.Models.NyTimesAPI;
import org.desperu.mynews.MyNewsTools;
import org.desperu.mynews.R;

import java.util.Date;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class NotificationsAlarmService extends Service {

    private static final int NOTIFICATION_ID = 1;

    private Disposable disposable;
    // Notification configuration.
    private String queryTerms;
    private String sections;
    // Must be set for retrofit request.
    private String beginDate;
    private String endDate;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.getNotificationConfigurationAndSetToFields();
        this.executeHttpRequestWithRetrofit();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }

    /**
     * Create notification, and set on click.
     * @param numResults Number of results for the search.
     */
    private void createNotification(int numResults) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), AppNotifications.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_black_news_logo)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(numResults+getString(R.string.notification_text))
                .setAutoCancel(true);

        // Create intent for notification click.
        Intent resultIntent = new Intent(this, SearchResultsActivity.class)
                .putExtra(SearchResultsActivity.QUERY_TERMS, queryTerms)
                .putExtra(SearchResultsActivity.BEGIN_DATE, beginDate)
                .putExtra(SearchResultsActivity.END_DATE, endDate)
                .putExtra(SearchResultsActivity.SECTIONS, sections);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(SearchResultsActivity.class);

        // Adds the intent that starts the activity to the top of stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Get notification configuration saved and set to fields.
     */
    private void getNotificationConfigurationAndSetToFields() {
        this.queryTerms = MyNewsPrefs.getString(getBaseContext(), MyNewsTools.Keys.NOTIFICATION_QUERY_TERMS, null);
        this.sections = MyNewsPrefs.getString(getBaseContext(), MyNewsTools.Keys.NOTIFICATION_SECTIONS, null);
        this.beginDate = MyNewsUtils.dateToStringForNyTimes(MyNewsUtils.stringToDate(MyNewsTools.Constant.BEGIN_DATE_DEFAULT));
        this.endDate = MyNewsUtils.dateToStringForNyTimes(new Date());
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    /**
     * Execute Http request with retrofit, notification request.
     */
    private void executeHttpRequestWithRetrofit() {
        disposable = NyTimesStreams.streamFetchNyTimesSearch(queryTerms, beginDate, endDate, sections, MyNewsTools.Constant.SORT_BY)
                .subscribeWith(new DisposableObserver<NyTimesAPI>() {
            @Override
            public void onNext(NyTimesAPI nyTimesAPI) {
                createNotification(nyTimesAPI.getResponse().getResults().size());
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