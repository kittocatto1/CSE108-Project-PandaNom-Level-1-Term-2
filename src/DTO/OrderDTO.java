package DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class OrderDTO implements Serializable{
    HashMap<Food,Integer> foodCount;
    String user;
    LocalDateTime timeSent;
    String restaurantName;
    
    public String getUser() {
        return user;
    }
    @Override
    public String toString()
    {
        for (Map.Entry<Food, Integer> entry : foodCount.entrySet()) {
        Food food = entry.getKey();
        Integer count = entry.getValue();
        System.out.println("Food: " + food + ", Count: " + count);
        }
        System.out.println(foodCount.size());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = timeSent.format(formatter);
        System.out.println("Time sent: " + formattedTime);
        return "Username: "+user;
    }
    
    public void setTimeSent(LocalDateTime timeSent) {
        this.timeSent = timeSent;
    }
    public LocalDateTime getTimeSent() {
        return timeSent;
    }
    public HashMap<Food, Integer> getFoodCount() {
        return foodCount;
    }
    public void setFoodCount(HashMap<Food, Integer> foodCount) {
        this.foodCount = foodCount;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    
}
