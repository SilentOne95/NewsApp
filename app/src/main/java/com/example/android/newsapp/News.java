package com.example.android.newsapp;

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

    /**
     * Constructs a new {@link News} object.
     * @param title is the title of the article
     * @param author is the author of the article
     */
    public News(String title, String author) {
        mTitle = title;
        mAuthor = author;
    }

    /** Returns the title of the article. */
    public String getTitle() { return mTitle; }

    /** Returns the author of the article */
    public String getAuthor() { return mAuthor; }

}
