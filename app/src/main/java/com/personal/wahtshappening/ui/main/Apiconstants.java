package com.personal.wahtshappening.ui.main;

import java.text.SimpleDateFormat;


/**
 * Created by ${Vignesh} on 2019-06-18.
 */


public class Apiconstants {

    public static String APIKEY = "4f5cd5bee9524ef385cbaa5e221c87ab";
    public static String API = "https://newsapi.org/v2/everything?apiKey=" + APIKEY + "&q=";

    public static SimpleDateFormat UTCFOMRAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
}
