package com.example.ismydatasecure;

public class WebsiteItem {
    private String mImageUrl;
    private String mTitle;
    private int mPwncount;
    private String mDate;
    private boolean expanded;
    private String text;



    public WebsiteItem(String mImageUrl, String mTitle, int mPwncount, String mDate, String text) {
        this.mImageUrl = mImageUrl;
        this.mTitle = mTitle;
        this.mPwncount = mPwncount;
        this.mDate = mDate;
        this.text = text;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getmPwncount() {
        return mPwncount;
    }

    public void setmPwncount(int mPwncount) {
        this.mPwncount = mPwncount;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}
