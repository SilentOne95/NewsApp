package com.example.android.newsapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private static final String LOG_TAG = NewsActivity.class.getName();

    String apiKey = BuildConfig.ApiKey;
    /** URL for articles data from the GUARDIAN dataset */
    private final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search?section=football&show-tags=contributor&api-key=a7fc03d0-b20b-4f4a-a947-d7b1bd9fb2d3";

    /** Adapter for the list of articles */
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        // Start the AsyncTask to fetch the news data
        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(GUARDIAN_REQUEST_URL);
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the list of earthquakes in the response.
     *
     * AsyncTask has three generic parameters: the input type, a type used for progress updates, and
     * an output type. Our task will take a String URL, and return an Article. We won't do
     * progress updates, so the second generic is just Void.
     *
     * We'll only override two of the methods of AsyncTask: doInBackground() and onPostExecute().
     * The doInBackground() method runs on a background thread, so it can run long-running code
     * (like network activity), without interfering with the responsiveness of the app.
     * Then onPostExecute() is passed the result of doInBackground() method, but runs on the
     * UI thread, so it can use the produced data to update the UI.
     */
    private class NewsAsyncTask extends AsyncTask<String, Void, List<News>> {

        /**
         * This method runs on a background thread and performs the network request.
         * We should not update the UI from a background thread, so we return a list of
         * {@link News} as the result.
         */
        @Override
        protected List<News> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            List<News> result = QueryUtils.fetchNewsData(urls[0]);
            return result;
        }

        /**
         * This method runs on the main UI thread after the background work has been
         * completed. This method receives as input, the return value from the doInBackground()
         * method. First we clear out the adapter, to get rid of earthquake data from a previous
         * query to GUARDIAN. Then we update the adapter with the new list of articles,
         * which will trigger the ListView to re-populate its list items.
         */
        @Override
        protected void onPostExecute(List<News> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
