package com.study.restaurant.model;

public class StoryDetail {

    String story_detail_id;
    String story_id;
    String store_id;
    String image;
    String comment;
    String page;
    String author;
    String contents;

    public String getStory_detail_id() {
        return story_detail_id;
    }

    public void setStory_detail_id(String story_detail_id) {
        this.story_detail_id = story_detail_id;
    }

    public String getStory_id() {
        return story_id;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}