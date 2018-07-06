package com.example.android.newsapp;

import android.content.Context;
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
    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    // TODO: ViewHolder
//    public class CustomName {
//        private String section;
//        private String title;
//        private String author;
//        private String date;
//
//        public CustomName(String section, String title, String author, String date) {
//            this.section = section;
//            this.title = title;
//            this.author = author;
//            this.date = date;
//        }
//
//        public String getSection() { return section; }
//        public String getTitle() { return title; }
//        public String getAuthor() { return author; }
//        public String getDate() { return date; }
//
//        public void setSection(String section) { this.section = section; }
//        public void setTitle(String title) { this.title = title; }
//        public void setAuthor(String author) { this.author = author; }
//        public void setDate(String date) { this.date = date; }
//    }
//
//    static class ViewHolder {
//        private TextView sectionView;
//        private TextView titleView;
//        private TextView authorView;
//        private TextView dateView;
//    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of articles.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO: ViewHolder
//        ViewHolder holder;
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
//            holder = new ViewHolder();
//            holder.sectionView = convertView.findViewById(R.id.section);
//            holder.titleView = convertView.findViewById(R.id.title);
//            holder.authorView = convertView.findViewById(R.id.author);
//            holder.dateView = convertView.findViewById(R.id.date);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        CustomName customName = getItem(position);
//
//        holder.sectionView.setText(customName.getSection());
//        holder.titleView.setText(customName.getTitle());
//        holder.authorView.setText(customName.getAuthor());
//        holder.dateView.setText(customName.getDate());
//
//        return convertView;


        // Check if there is any existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent,false);
        }

        // Find the article at the given position in the list of articles
        News currentNews = getItem(position);

        // Find the TextView with view ID section
        TextView sectionView = listItemView.findViewById(R.id.section);
        // Display the section of the current article in that TextView
        sectionView.setText(currentNews.getSection());

        // Find the TextView with view ID title
        TextView titleView = listItemView.findViewById(R.id.title);
        // Display the title of the current article in that TextView
        titleView.setText(currentNews.getTitle());

        // Find the TextView with view ID author
        TextView authorView = listItemView.findViewById(R.id.author);
        // Display the author of the current article in that TextView
        authorView.setText(currentNews.getAuthor());

        // Find the TextView with view ID date
        TextView dateView = listItemView.findViewById(R.id.date);
        // Format date and then
        // display the date of the current article in that TextView
        dateView.setText(formatDate(currentNews.getDate()));

        return listItemView;
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