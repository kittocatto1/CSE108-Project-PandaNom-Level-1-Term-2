package DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable{
    private int id;
    private String name;
    private double score;
    private String price;
    private String zipCode;
    private String category1;
    private String category2;
    private String category3;
    private String password;
    private List<Food> menu;

    //constructor

    public Restaurant(String password,int id, String name, double score, String price, String zipCode, String category1, String category2, String category3)
    {
        this.id = id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.zipCode = zipCode;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.password = password;
        menu = new ArrayList<>();
    }
    
    //other functions
    @Override
    public String toString()
    {
        String s = "Restaurant information:\n" + "  ID: " + id + "\n  Name: " + name + "\n  Score: " + score + "\n  Price: " + price + "\n  Zip Code: " + zipCode + "\n  Categories: " + getCategory1();
        if(category2!=null)
        {
            s += (", " + category2);
        }
        if(category3!=null)
        {
            s += (", " + category3);
        }
        return s;
    }
    public void setFoodCountToZero(){
        for(Food food : menu){
            food.setOrderCount(0);
        }
    }
    //setters and getters

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setCategory1(String category1) {
        this.category1 = category1;
    }
    public void setCategory2(String category2) {
        this.category2 = category2;
    }
    public void setCategory3(String category3) {
        this.category3 = category3;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getScore() {
        return score;
    }
    public String getPrice() {
        return price;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getCategory1() {
        return category1;
    }
    public String getCategory2() {
        return category2;
    }
    public String getCategory3() {
        return category3;
    }

    public List<Food> getMenu() {
        return menu;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
