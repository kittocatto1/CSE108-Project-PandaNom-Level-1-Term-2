package DTO;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order implements Serializable{
    List<Food> foods;
    String user;
    LocalTime timeSent;

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public List<Food> getFoods() {
        return foods;
    }
    public String getUser() {
        return user;
    }
    @Override
    public String toString()
    {
        for(Food food: foods){
        System.out.println(food);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        String formattedTime = timeSent.format(formatter);
        System.out.println("Time sent: " + formattedTime);
        return "Username: "+user;
    }
    public void setTimeSent(LocalTime timeSent) {
        this.timeSent = timeSent;
    }
    public LocalTime getTimeSent() {
        return timeSent;
    }
    
}
