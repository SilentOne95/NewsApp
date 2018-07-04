package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of articles.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is any existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false);
        }

        // Find the article at the given position in the list of articles
        News currentNews = getItem(position);

        // Find the TextView with view ID title
        TextView titleView = listItemView.findViewById(R.id.title);
        // Display the title of the current article in that TextView
        titleView.setText(currentNews.getTitle());

        // Find the TextView with view ID author
        TextView authorView = listItemView.findViewById(R.id.author);
        // Display the author of the current article in that TextView
        authorView.setText(currentNews.getAuthor());

        // TODO: Fix issue with displaying date to the user
        // Create a new Date object from the time in milliseconds of the article
//        Date dateObject = new Date(currentNews.getDate());
        // Find the TextView with view ID date
//        TextView dateView = listItemView.findViewById(R.id.date);
        // Format the date string
//        String formattedDate = formatDate(dateObject);
        // Display the date of the current article in that TextView
//        dateView.setText(dateView);

        return listItemView;
    }

    /**
     * Return the formatted date string from a Date object
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

}