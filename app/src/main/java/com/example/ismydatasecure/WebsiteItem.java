package com.example.ismydatasecure;

public class WebsiteItem {
    private String mImageUrl;
    private String mTitle;
    private int mPwncount;
    private String mDate;



    public WebsiteItem(String mImageUrl, String mTitle, int mPwncount, String mDate) {
        this.mImageUrl = mImageUrl;
        this.mTitle = mTitle;
        this.mPwncount = mPwncount;
        this.mDate = mDate;

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
}
