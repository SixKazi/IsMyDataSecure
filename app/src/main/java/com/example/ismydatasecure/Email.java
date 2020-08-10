package com.example.ismydatasecure;

import org.json.JSONArray;
import org.json.JSONObject;

public class Email {
    private String address;
    private JSONArray breachedWebsites;


    public JSONArray getBreachedWebsites() {
        return breachedWebsites;
    }

    public void setBreachedWebsites(JSONArray breachedWebsites) {
        this.breachedWebsites = breachedWebsites;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public int getWebsitesLength()
    {
        if(breachedWebsites.isNull(0)) return -1;
        return breachedWebsites.length();
    }


    public Email(String input, JSONArray array) {
    }
}
