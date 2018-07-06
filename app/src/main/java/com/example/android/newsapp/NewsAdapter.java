package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * An {@link NewsAdapter} knows how to create a list item layout for each article
 * in the data source (a list of {@link News} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * Constructs a new {@link NewsAdapter}.
     * @param context of the app
     * @param news is the list of articles, which is the data source of the adapter
     */
    public NewsAdapter(@NonNull Context context, @NonNull List<News> news) {
        super(context, 0, news);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of articles.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        class ViewHolder {
            private TextView sectionView;
            private TextView titleView;
            private TextView authorView;
            private TextView dateView;
        }

        // TODO: ViewHolder
        ViewHolder holder;

        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.sectionView = listItemView.findViewById(R.id.section);
            holder.titleView = listItemView.findViewById(R.id.title);
            holder.authorView = listItemView.findViewById(R.id.author);
            holder.dateView = listItemView.findViewById(R.id.date);
            listItemView.setTag(holder);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }

        News currentNews = getItem(position);

        holder.sectionView.setText(currentNews.getSection());
        holder.titleView.setText(currentNews.getTitle());
        holder.authorView.setText(currentNews.getAuthor());
        holder.dateView.setText(formatDate(currentNews.getDate()));

        return listItemView;


        // Check if there is any existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
//        View listItemView = convertView;
//        if (listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.list_item, parent,false);
//        }
//
//        // Find the article at the given position in the list of articles
//        News currentNews = getItem(position);
//
//        // Find the TextView with view ID section
//        TextView sectionView = listItemView.findViewById(R.id.section);
//        // Display the section of the current article in that TextView
//        sectionView.setText(currentNews.getSection());
//
//        // Find the TextView with view ID title
//        TextView titleView = listItemView.findViewById(R.id.title);
//        // Display the title of the current article in that TextView
//        titleView.setText(currentNews.getTitle());
//
//        // Find the TextView with view ID author
//        TextView authorView = listItemView.findViewById(R.id.author);
//        // Display the author of the current article in that TextView
//        authorView.setText(currentNews.getAuthor());
//
//        // Find the TextView with view ID date
//        TextView dateView = listItemView.findViewById(R.id.date);
//        // Format date and then
//        // display the date of the current article in that TextView
//        dateView.setText(formatDate(currentNews.getDate()));
//
//        return listItemView;
    }

    /**
     * Return the formatted date string from a Date object
     */
    private String formatDate(String dateTime) {
        String[] dateParts = dateTime.split("T");
        String yearMonthDay = dateParts[0];
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = parser.parse(yearMonthDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat timeFormat = new SimpleDateFormat("MMM dd, yyyy");
        String finalDate = timeFormat.format(myDate).toUpperCase();

        return finalDate;
    }

}