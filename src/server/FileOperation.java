package server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import DTO.*;

public class FileOperation {
    public static void readCustomerLoginInfo(HashMap<String,String> customerMap) throws FileNotFoundException,IOException {
        BufferedReader br;
            br = new BufferedReader(new FileReader("CustomerLogin.txt"));
            while (true) {
            String line;
                line = br.readLine();
                if (line == null) break;
                String [] loginInfo = line.split(",");
                customerMap.put(loginInfo[0], loginInfo[1]);
            }
            br.close(); 
    }

    public static void addCustomer(String username, String password,HashMap<String,String> customerMap) throws Exception{
        BufferedWriter bw = new BufferedWriter(new FileWriter("CustomerLogin.txt"));
       
        for(String entry : customerMap.keySet()) {
            String name = entry;
            String pass = customerMap.get(entry);
            System.out.println(name+" "+pass);
            String line = name+","+pass;
            System.out.println(line);
            bw.write(line);
            bw.write(System.lineSeparator());
        }
        // String line = ""+username+","+password;
        // bw.write(line);
        bw.close();
    }

    public static void readRestaurantInfo(List<Restaurant> restaurantList, HashMap<String,String> restaurantMap) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("restaurant.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] array = line.split(",");
            restaurantMap.put(array[2], array[0]);
            if(array.length==7){
                Restaurant newRestaurant = new Restaurant(array[0],Integer.parseInt(array[1]), array[2], Double.parseDouble(array[3]), array[4], array[5], array[6], null, null);
                addRestaurant(newRestaurant, restaurantList);
            }
            else if(array.length==8){
                Restaurant newRestaurant = new Restaurant(array[0],Integer.parseInt(array[1]), array[2], Double.parseDouble(array[3]), array[4], array[5], array[6], array[7], null);
                addRestaurant(newRestaurant, restaurantList);
            }
            else
            {
                Restaurant newRestaurant = new Restaurant(array[0],Integer.parseInt(array[1]), array[2], Double.parseDouble(array[3]), array[4], array[5], array[6], array[7], array[8]);
                addRestaurant(newRestaurant, restaurantList);
            }
        }
        br.close();
    }

    public static boolean addRestaurant(Restaurant newRestaurant,List<Restaurant> restaurantList) {
        for(var restaurant : restaurantList)
        {
            if(restaurant.getId()==newRestaurant.getId() || restaurant.getName().equalsIgnoreCase(newRestaurant.getName()))
            {
                return false;
            }
        }
        
        restaurantList.add(newRestaurant);
        return true;
    }

    public static void readFoodInfo(List<Restaurant> restaurantList) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("menu.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] array = line.split(",");
            Food newFood = new Food(Integer.parseInt(array[0]), array[1], array[2], Double.parseDouble(array[3]));
            addFood(newFood,restaurantList);
        }
        br.close();
    }

    public static boolean addFood(Food newFood, List<Restaurant> restaurantList) {
        boolean restaurantFound = false;
        Restaurant restaurantObj = null;
        for(Restaurant restaurant : restaurantList)
        {
            if(newFood.getRestaurantId()==restaurant.getId())
            {
                restaurantFound = true;
                restaurantObj = restaurant;
                break;
            }
        }
        boolean added = false;
        if(restaurantFound)
        {
            List<Food> menu = restaurantObj.getMenu();
            for(Food food : menu)
            {

                if(newFood.getCategory().equalsIgnoreCase(food.getCategory()) && newFood.getName().equalsIgnoreCase(food.getName()))
                    {
                        return added;
                    }

            }
            newFood.setRestaurantName(restaurantObj.getName());
            menu.add(newFood);
            added = true;
        }
        return added;
    }

    public static void addRestaurantsToFile(List<Restaurant> restaurantList) throws Exception
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter("restaurant.txt"));
        for(Restaurant restaurant : restaurantList){
        String line = restaurant.getPassword()+","+restaurant.getId()+","+restaurant.getName()+","+restaurant.getScore()+","+restaurant.getPrice()+","+restaurant.getZipCode()+","+restaurant.getCategory1();
        if(restaurant.getCategory2()!=null)
        {
            line += (","+restaurant.getCategory2());
        }
        else
        {
            line += ",";
        }
        if(restaurant.getCategory3()!=null)
        {
            line += (","+restaurant.getCategory3());
        }
        else
        {
            line += ",";
        }
        bw.write(line);
        bw.write(System.lineSeparator());
       
        }
        bw.close();
    }
}
