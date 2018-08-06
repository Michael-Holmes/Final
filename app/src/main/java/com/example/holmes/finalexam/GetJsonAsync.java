package com.example.holmes.finalexam;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetJsonAsync extends AsyncTask<String, Void, List<String>> {

    private OnTaskCompleted taskCompleted;

    public GetJsonAsync(OnTaskCompleted taskCompleted) {this.taskCompleted = taskCompleted;}

    @Override
    protected List<String> doInBackground(String... strings) {



        HttpURLConnection connection = null;
        List<String> result = new ArrayList<>();
        try {
            String urlString = String.format("",strings[0]);
            Log.d("demo",urlString);
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");

                JSONObject objObject = new JSONObject(json);
                //JSONArray articles = objObject.getJSONArray("articles");

//                for (int i=0;i<articles.length();i++) {
//                    JSONObject articleJson = articles.getJSONObject(i);
//                    article.author = articleJson.getString("author").trim();
//                    article.urlToImage = articleJson.getString("urlToImage").trim();
//                    article.urlLink = articleJson.getString("url").trim();
//                    article.title = articleJson.getString("title").trim();
//                    article.publishedAt = articleJson.getString("publishedAt").trim();
//
//                    result.add(article);
//                }
            }
        } catch (Exception e) {
            //Handle Exceptions
        } finally {
            //Close the connection

            connection.disconnect();
        }

        return result;
    }

    public interface OnTaskCompleted{
        void onTaskCompleted(List<String> response);
    }
}
