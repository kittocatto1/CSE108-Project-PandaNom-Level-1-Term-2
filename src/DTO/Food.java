package DTO;

import java.io.Serializable;

public class Food implements Serializable {
    private int restaurantId;
    private String category;
    private String name;
    private double price;
    private int orderCount;
    private String restaurantName;

    public Food(int restaurantId, String category, String name, double price)
    {
        this.restaurantId = restaurantId;
        this.category = category;
        this.name = name;
        this.price = price;
        orderCount = 0;
    }

    @Override
    public String toString()
    {
        return "Food Information:" + "\n  Restaurant ID: " + restaurantId  + "\n  Restaurant Name: " + restaurantName + "\n  Category: " +  category + "\n  Name: " + name + "\n  Price: " + price+"\n Count: "+ orderCount;
    }

    //getters and setters

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getRestaurantId() {
        return restaurantId;
    }
    public String getCategory() {
        return category;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

}
