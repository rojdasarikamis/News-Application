package com.androidevreni.rsstutorial.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mustafademir on 27.06.2016.
 */

/**
 * BİR YAZI VEYA POSTU MODELLEDİĞİMİZ NESNEMİZ
 */
public class Post {

    private String title;
    private String link;
    private String description;
    private String imageUrl;
    private String pubDate;
    private List<String> category = new ArrayList<String>();
    private String guid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public void addCategoryItem(String categoryItem) {
        this.category.add(categoryItem);
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

}
