package com.study.restaurant.model;

public class News {
    String news_id;
    String profile_img;
    String user_name;
    String user_writing;
    String user_follower;
    String hash_tag;
    String score;
    String contents;
    String like_count;
    String reply_count;
    String date;
    String user_id;
    String tag1;
    String tag2;
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_writing() {
        return user_writing;
    }

    public void setUser_writing(String user_writing) {
        this.user_writing = user_writing;
    }

    public String getUser_follower() {
        return user_follower;
    }

    public void setUser_follower(String user_follower) {
        this.user_follower = user_follower;
    }

    public String getHash_tag() {
        return hash_tag;
    }

    public void setHash_tag(String hash_tag) {
        this.hash_tag = hash_tag;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    String domain = "http://sarang628.iptime.org:83/image_upload/";

    public String getImage1() {
        if(url == null)
            setUrl("");
        return (url.split(",").length) > 0 ? domain + url.split(",")[0] : "";
    }

    public String getImage2() {
        return (url.split(",").length) > 1 ? domain + url.split(",")[1] : "";
    }

    public String getImage3() {
        return (url.split(",").length) > 2 ? domain + url.split(",")[2] : "";
    }

    public String getImage4() {
        return (url.split(",").length) > 3 ? domain + url.split(",")[3] : "";
    }

    public String getImage5() {
        return (url.split(",").length) > 4 ? domain + url.split(",")[4] : "";
    }

    public String getImage6() {
        return (url.split(",").length) > 5 ? domain + url.split(",")[5] : "";
    }

    public String getImage7() {
        return (url.split(",").length) > 6 ? domain + url.split(",")[6] : "";
    }

    public String getImage8() {
        return (url.split(",").length) > 7 ? domain + url.split(",")[7] : "";
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getReply_count() {
        return reply_count;
    }

    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
