package com.example.sergeyvankovich.goodfarm.models;

import java.util.List;

public class Set {
    private String name;
    private String description;
    private double price;
    private String imgUrl;
    private List<Product> productList;

    public Set(String name, String description, double price, String imgUrl, List<Product> productList) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productList = productList;
        this.imgUrl = imgUrl;
    }

    public Set(String name, String description, double price, String imgUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
