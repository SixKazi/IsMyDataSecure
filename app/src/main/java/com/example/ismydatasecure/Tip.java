package com.example.ismydatasecure;

public class Tip {
    private String title;
    private String category;
    private String info;
    private String logo;
    private boolean expanded;

    public Tip(String title, String category, String info) {
        this.title = title;
        this.category = category;
        this.info = info;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

}
