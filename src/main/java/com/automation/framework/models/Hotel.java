package com.automation.framework.models;

import java.util.HashMap;
import java.util.List;

public class Hotel {
    private String name;
    private int star;
    private String companyOffer;
    private String price;
    private HashMap<String, List<String>> convenience;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getCompanyOffer() {
        return companyOffer;
    }

    public void setCompanyOffer(String companyOffer) {
        this.companyOffer = companyOffer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public HashMap<String, List<String>> getConvenience() {
        return convenience;
    }

    public void setConvenience(HashMap<String, List<String>> convenience) {
        this.convenience = convenience;
    }
}
