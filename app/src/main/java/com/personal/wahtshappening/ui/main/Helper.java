package com.personal.wahtshappening.ui.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.wahtshappening.ui.main.jdo.NewsJDO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/**
 * Created by ${Vignesh} on 2019-06-18.
 */
public class Helper {

    String TAG = getClass().getName();

    ObjectMapper mObjectMapper;

    public Helper() {
        mObjectMapper = new ObjectMapper();
    }


    /**
     *
     * @param pCategory
     * @return news response
     * @throws IOException
     */

    public String fetchNews(String pCategory) throws IOException {

        URL  lNewsURL                       = new URL(Apiconstants.API + pCategory);
        HttpURLConnection urlConnection     = (HttpURLConnection) lNewsURL.openConnection() ;
        String eachLine = null;
        StringBuilder lResponse             = new StringBuilder();

        urlConnection.setRequestMethod("GET");


        Log.d(TAG, "  parseJSONData URL " + lNewsURL  + "    " + urlConnection.getResponseCode());

        if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK ){
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((eachLine = bufferedReader.readLine()) != null) {
                lResponse.append(eachLine);
            }
        }


        return lResponse.toString();

    }


    /**
     *
     * @param articles
     * @return List of news JDO
     * @throws IOException
     */
    public List<NewsJDO> parseJSONData(String articles) throws IOException {
        Log.d(TAG, "  parseJSONData  " + articles);
        JsonNode lRootNode = null;
        lRootNode = mObjectMapper.readValue(articles.toString(), JsonNode.class);
        TypeReference<List<NewsJDO>> lType = new TypeReference<List<NewsJDO>>() {
        };

        List<NewsJDO> lNewsList = (List<NewsJDO>) mObjectMapper.readValue(lRootNode.findValue("articles").toString(), lType);

        Log.d(TAG, lNewsList.toString());

        return lNewsList;
    }


    /**
     *
     * @param context
     * @return device internet status
     */

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

}
