package com.personal.wahtshappening.ui.main.jdo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.personal.wahtshappening.ui.main.Apiconstants;

import java.text.ParseException;
import java.util.Date;


/**
 * Created by ${Vignesh} on 2019-06-18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsJDO {

    @JsonProperty("author")
    String author;

    @JsonProperty("title")
    String title;

    @JsonProperty("description")
    String description;

    @JsonProperty("url")
    String url;

    @JsonProperty("urlToImage")
    String imageUrl;

    @JsonProperty("publishedAt")
    String publishedAt;

    @JsonProperty("content")
    String content;

    @JsonProperty("source")
    Source source;


    public String getAuthor() {

        return author;
    }


    public void setAuthor(String author) {

        this.author = author;
    }


    public String getTitle() {

        return title;
    }


    public void setTitle(String title) {

        this.title = title;
    }


    public String getDescription() {

        return description;
    }


    public void setDescription(String description) {

        this.description = description;
    }


    public String getUrl() {

        return url;
    }


    public void setUrl(String url) {

        this.url = url;
    }


    public String getImageUrl() {

        return imageUrl;
    }


    public void setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;
    }


    public Date getPublishedAt() throws ParseException {
        return Apiconstants.UTCFOMRAT.parse(publishedAt);
    }


    public void setPublishedAt(String publishedAt) {

        this.publishedAt = publishedAt;
    }


    public String getContent() {

        return content;
    }


    public void setContent(String content) {

        this.content = content;
    }


    public Source getSource() {

        return source;
    }


    public void setSource(Source source) {

        this.source = source;
    }


    @Override
    public String toString() {

        return "NewsJDO{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                ", source=" + source +
                '}';
    }
}


class Source {


    String id, name;


    public String getId() {

        return id;
    }


    public String getName() {

        return name;
    }
}