package io.github.memfis19.cadar.internal.ui.list.adapter.holder;

import android.os.Handler;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

import io.github.memfis19.cadar.R;

/**
 * Created by memfis on 9/6/16.
 */
public class WeekTitleHolder extends RecyclerView.ViewHolder {

    private TextView title;

    private Handler backgroundHandler;
    private Handler uiHandler;
    private final String DATE_FORMAT = "dd MMM";

    public WeekTitleHolder(View itemView, Handler backgroundHandler, Handler uiHandler) {
        super(itemView);

        this.backgroundHandler = backgroundHandler;
        this.uiHandler = uiHandler;

        title = (TextView) itemView.findViewById(R.id.week_title);
    }

    public void bindView(final Pair<Calendar, Calendar> period) {
        backgroundHandler.post(new Runnable() {
            @Override
            public void run() {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(title.getContext().getString(R.string.calendar_week));
                stringBuilder.append(" ");
                stringBuilder.append(period.first.get(Calendar.WEEK_OF_YEAR));
                stringBuilder.append(", ");
                stringBuilder.append(DateFormat.format(DATE_FORMAT, period.first));
                stringBuilder.append(" - ");
                stringBuilder.append(DateFormat.format(DATE_FORMAT, period.second));

                final Spannable date = new SpannableString(stringBuilder.toString());

                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        title.setText(date);
                    }
                });
            }
        });
    }
}
