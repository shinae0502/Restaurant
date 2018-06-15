package com.study.restaurant.model;

public class User {
    public String email;
    public String login_platform;
    public String picture;
    public String name;

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("email = " + email);
        stringBuffer.append("\n");
        stringBuffer.append("login_platform = " + login_platform);
        stringBuffer.append("\n");
        stringBuffer.append("picture = " + picture);
        stringBuffer.append("\n");
        stringBuffer.append("name = " + name);

        return stringBuffer.toString();
    }
}
