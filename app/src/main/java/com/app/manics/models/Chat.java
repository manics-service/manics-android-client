package com.app.manics.models;


import com.google.gson.annotations.SerializedName;

public class Chat {
    @SerializedName("chatId")
    private int chatId;
    @SerializedName("title")
    private String title;
    @SerializedName("userCount")
    private short userCount;
    @SerializedName("image50")
    private String image50;
    @SerializedName("image100")
    private String image100;
    @SerializedName("image200")
    private String image200;

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getUserCount() {
        return userCount;
    }

    public void setUserCount(short userCount) {
        this.userCount = userCount;
    }

    public String getImage50() {
        return image50;
    }

    public void setImage50(String image50) {
        this.image50 = image50;
    }

    public String getImage100() {
        return image100;
    }

    public void setImage100(String image100) {
        this.image100 = image100;
    }

    public String getImage200() {
        return image200;
    }

    public void setImage200(String image200) {
        this.image200 = image200;
    }

    public boolean isTitleImage(){
        final String emptyImg = "null";

        if (emptyImg.equals(image50) && emptyImg.equals(image100) && emptyImg.equals(image200)) {
            return false;
        }

        return true;
    }
}
