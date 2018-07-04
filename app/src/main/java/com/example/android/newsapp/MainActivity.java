package com.example.android.newsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView list_view;

    ArrayList<HashMap<String, String>> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articleList = new ArrayList<>();
        list_view = findViewById(R.id.list);

        new GetArticle().execute();
    }

    private class GetArticle extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String apiKey = BuildConfig.ApiKey;
            String url = "https://content.guardianapis.com/search?section=football&show-tags=contributor&api-key=" + apiKey;
            String jsonString = "";
            try {
                jsonString = sh.makeHttpRequest(createUrl(url));
            } catch (IOException e) {
                return null;
            }

            Log.e(TAG, "Response from url: " + jsonString);
            if (jsonString != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonString);
                    JSONObject jsonTest = jsonObj.getJSONObject("response");

                    JSONArray results = jsonTest.getJSONArray("results");

                    // looping through all objects
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject c = results.getJSONObject(i);
                        String title = c.getString("webTitle");

                        JSONArray tags = c.getJSONArray("tags");
                        JSONObject obj = tags.getJSONObject(0);
                        String authorName = obj.optString("firstName");
                        String authorLastName = obj.optString("lastName");

                        StringBuilder builder = new StringBuilder();
                        builder.append(authorName).append(" ").append(authorLastName);
                        String fullName = builder.toString();


                        // tmp hash map for a single article
                        HashMap<String, String> result = new HashMap<>();

                        // add each child node to HashMap key => value
                        result.put("title", title);
                        result.put("author", fullName);

                        // adding a article to our article list
                        articleList.add(result);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server.",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                return null;
            }
            return url;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, articleList,
                    R.layout.list_item, new String[]{"title", "author"},
                    new int[]{R.id.title, R.id.author});
            list_view.setAdapter(adapter);
        }
    }
}
