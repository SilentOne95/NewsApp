package com.example.android.newsapp;

import java.util.Date;

/**
 * An {@link News} object contains information related to a single article.
 */
public class News {

    /** Title of the article */
    private String mTitle;

    /** Author of the article */
    private String mAuthor;

    /** Time of the article publication */


    /** Website URL of the full article */
    private String mUrl;
    /**
     * Constructs a new {@link News} object.
     * @param title is the title of the article
     * @param author is the author of the article
     * @param date is the publication date of the article
     * @param url is the url to the article
     */
    public News(String title, String author, String url) {
        mTitle = title;
        mAuthor = author;
        mUrl = url;
    }

    /** Returns the title of the article. */
    public String getTitle() { return mTitle; }

    /** Returns the author of the article */
    public String getAuthor() { return mAuthor; }

    /** Returns the publication date of the article */


    /** Returns the url to the article */
    public String getUrl() { return mUrl; }

}