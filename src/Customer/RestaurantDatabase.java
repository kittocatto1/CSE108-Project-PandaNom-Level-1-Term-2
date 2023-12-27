package Customer;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import DTO.Restaurant;
import DTO.Food;

public class RestaurantDatabase {
    List<Restaurant> restaurantList;

    RestaurantDatabase() {
        restaurantList = new ArrayList<>();
    }
    RestaurantDatabase(List<Restaurant> restaurantList){
        this.restaurantList = restaurantList; 
    }
    public void setRestaurantList(List<Restaurant> list){
        restaurantList = list;
    }
    
    public boolean addRestaurant(Restaurant newRestaurant) {
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

    public boolean addFood(Food newFood) {
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
            menu.add(newFood);
            added = true;
        }
        return added;
    }

    public synchronized ArrayList<Restaurant> searchRestaurantByName(String name) {
        ArrayList<Restaurant> restaurantsFound = new ArrayList<>();
        for(Restaurant restaurant : restaurantList) {
            if (restaurant.getName().equalsIgnoreCase(name)) {
                restaurantsFound.add(restaurant);
                break;
            }
        }
        return restaurantsFound;
    }

    public synchronized ArrayList<Restaurant> searchRestaurantByScore(double min, double max) {
        ArrayList<Restaurant> restaurantsFound = new ArrayList<>();
        for(Restaurant restaurant : restaurantList) {
            double x = restaurant.getScore();
            if (x>=min && x<=max) {
                restaurantsFound.add(restaurant);
            }
        }
        return restaurantsFound;
    }

    public synchronized ArrayList<Restaurant> searchRestaurantByCategory(String category) {
        ArrayList<Restaurant> restaurantsFound = new ArrayList<>();
        for(Restaurant restaurant : restaurantList) {
            if(restaurant.getCategory1().toLowerCase().contains(category.toLowerCase())){
                restaurantsFound.add(restaurant);
            }
            else if(restaurant.getCategory2()!=null && restaurant.getCategory2().toLowerCase().contains(category.toLowerCase())) {
                restaurantsFound.add(restaurant);             
            }

            else if(restaurant.getCategory3()!=null && restaurant.getCategory3().toLowerCase().contains(category.toLowerCase())) {
                restaurantsFound.add(restaurant);
            }
        }
        return restaurantsFound;
    }

    public synchronized ArrayList<Restaurant> searchRestaurantByPrice(String price) {
       ArrayList<Restaurant> restaurantsFound = new ArrayList<>();
        for(Restaurant restaurant : restaurantList) {
            if(price.equals(restaurant.getPrice())) {
                restaurantsFound.add(restaurant);
            }
        }
        return restaurantsFound;
    }

    public synchronized ArrayList<Restaurant> searchRestaurantByZipCode(String zipCode) {
        ArrayList<Restaurant> restaurantsFound = new ArrayList<>();
        for(Restaurant restaurant : restaurantList) {
            if(zipCode.equals(restaurant.getZipCode())) {
                restaurantsFound.add(restaurant);
            }
        }
        return restaurantsFound;
    }

    public Set<String> getDistinctCategories()
    {
        Set<String> distinctCategories = new HashSet<>();
        for(Restaurant restaurant : restaurantList)
        {
            distinctCategories.add(restaurant.getCategory1().toLowerCase());
            if(restaurant.getCategory2()!=null )
            {
                distinctCategories.add(restaurant.getCategory2().toLowerCase());
            }
            if(restaurant.getCategory3()!=null)
            {
                distinctCategories.add(restaurant.getCategory3().toLowerCase());
            }
        }
        return distinctCategories;
    }

    public synchronized ArrayList<Food> searchFoodByName(String name) {
        ArrayList<Food> foodsFound = new ArrayList<>();
        for(var restaurant : restaurantList)
        {
            List<Food> menu = restaurant.getMenu();
            for(var food : menu){
                if(food.getName().toLowerCase().contains(name.toLowerCase())){
                    foodsFound.add(food);
                }
            }
        }
        return foodsFound;
    }
    
    public synchronized ArrayList<Food> searchFoodByNameInARestaurant(String foodName, String restaurantName) {
        ArrayList<Food> foodsFound = new ArrayList<>();
        Restaurant restaurantObj = null;
        for(var restaurant : restaurantList)
        {
            if(restaurantName.equalsIgnoreCase(restaurant.getName()))
            {
                restaurantObj = restaurant;
                break;
            }
        }
        if(restaurantObj!=null)
        {
            List<Food> menu = restaurantObj.getMenu();
            for(var food : menu) {
            if (food.getName().toLowerCase().contains(foodName.toLowerCase())){
                foodsFound.add(food);
            }
            }
        }
        return foodsFound;
    }
    
    public synchronized ArrayList<Food> searchFoodByCategory(String category) {
        ArrayList<Food> foodsFound = new ArrayList<>();
        for(var restaurant : restaurantList)
        {
            List<Food> menu = restaurant.getMenu();
            for(var food : menu){
                if(food.getCategory().toLowerCase().contains(category.toLowerCase())){
                    foodsFound.add(food);
                }
            }
        }
        return foodsFound;
    }
    
    public synchronized ArrayList<Food> searchFoodByCategoryInARestaurant(String category, String restaurantName) {
        ArrayList<Food> foodsFound = new ArrayList<>();
        Restaurant restaurantObj = null;
        for(Restaurant restaurant : restaurantList)
        {
            if(restaurantName.equalsIgnoreCase(restaurant.getName()))
            {
                restaurantObj = restaurant;
                break;
            }
        }
        if(restaurantObj!=null)
        {
            List<Food> menu = restaurantObj.getMenu();
            for(Food food : menu) {
                if(food.getCategory().toLowerCase().contains(category.toLowerCase())){
                    foodsFound.add(food);
                }
            }
        }
        return foodsFound;
    }
    
    public synchronized ArrayList<Food> searchFoodByPrice(double min, double max){
        ArrayList<Food> foodsFound = new ArrayList<>();
        for(var restaurant : restaurantList)
        {
            List<Food> menu = restaurant.getMenu();
            for(var food : menu){
                double x = food.getPrice();
                if (x>=min && x<=max) {
                    foodsFound.add(food);
                }
            }
        }
        return foodsFound;
    }

    public synchronized ArrayList<Food> searchFoodByPriceRangeInARestaurant(double min, double max, String restaurantName) {
        ArrayList<Food> foodsFound = new ArrayList<>();
        Restaurant restaurantObj = null;
        for(var restaurant : restaurantList)
        {
            if(restaurantName.equalsIgnoreCase(restaurant.getName()))
            {
                restaurantObj = restaurant;
                break;
            }
        }
        if(restaurantObj!=null)
        {
            List<Food> menu = restaurantObj.getMenu();
            for(var food : menu) {
                double x = food.getPrice();
                if (x>=min && x<=max) {
                    foodsFound.add(food);
                }
            }
        }
        return foodsFound;
    }
    
    public synchronized ArrayList<Food> costliestFoodItemsInRestaurant(String restaurantName) {
        ArrayList<Food> foodsFound = new ArrayList<>();
        Restaurant restaurantObj = null;
        for(var restaurant : restaurantList)
        {
            if(restaurantName.equalsIgnoreCase(restaurant.getName()))
            {
                restaurantObj = restaurant;
                break;
            }
        }
        if(restaurantObj!=null)
        {
            List<Food> menu = restaurantObj.getMenu();
            double maxPrice = 0;
            for(var food : menu)
            {
                if(maxPrice<food.getPrice())
                {
                    maxPrice = food.getPrice();
                }
            }
            for(Food food : menu)
            {
                if(maxPrice==food.getPrice())
                {
                    foodsFound.add(food);
                }
            }
        }
        return foodsFound;
    }

    public synchronized ArrayList<Food> cheapestFoodItemsInRestaurant(String restaurantName,double epsilon) {
        ArrayList<Food> foodsFound = new ArrayList<>();
        Restaurant restaurantObj = null;
        for(var restaurant : restaurantList)
        {
            if(restaurantName.equalsIgnoreCase(restaurant.getName()))
            {
                restaurantObj = restaurant;
                break;
            }
        }
        if(restaurantObj!=null)
        {
            List<Food> menu = restaurantObj.getMenu();
            double minPrice = Double.MAX_VALUE;
            // for(var food : menu)
            // {
            //     if(minPrice>food.getPrice())
            //     {
            //         minPrice = food.getPrice();
            //     }
            // }
            // for(Food food : menu)
            // {
            //     if(minPrice==food.getPrice())
            //     {
            //         foodsFound.add(food);
            //     }
            // }
            for (var food : menu) {
                if (food.getPrice() < minPrice - epsilon) {
                    minPrice = food.getPrice();
                }
            }
            for (Food food : menu) {
                if (Math.abs(minPrice - food.getPrice()) < epsilon) {
                    foodsFound.add(food);
                }
            }
        }
        return foodsFound;
    }

    public synchronized HashMap<String,Integer> getRestaurantsAndTotalFoodItems(){
        HashMap<String,Integer> hashMap = new HashMap<>();
        for(Restaurant restaurant : restaurantList)
        {
            List<Food> menu = restaurant.getMenu();
            hashMap.put(restaurant.getName(), menu.size());
        }
        return hashMap;
    }

    public synchronized ArrayList<String> getRestaurantNames()
    {
        ArrayList<String> restaurantNames = new ArrayList<>();
        for(Restaurant restaurant : restaurantList)
        {
            restaurantNames.add(restaurant.getName());
        }
        return restaurantNames;

    }
    
    public void readRestaurantInfo(List<Restaurant> restaurantList, HashMap<String,String> restaurantMap) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("restaurant.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] array = line.split(",");
            restaurantMap.put(array[2], array[0]);
            if(array.length==7){
                Restaurant newRestaurant = new Restaurant(array[0],Integer.parseInt(array[1]), array[2], Double.parseDouble(array[3]), array[4], array[5], array[6], null, null);
                addRestaurant(newRestaurant);
            }
            else if(array.length==8){
                Restaurant newRestaurant = new Restaurant(array[0],Integer.parseInt(array[1]), array[2], Double.parseDouble(array[3]), array[4], array[5], array[6], array[7], null);
                addRestaurant(newRestaurant);
            }
            else
            {
                Restaurant newRestaurant = new Restaurant(array[0],Integer.parseInt(array[1]), array[2], Double.parseDouble(array[3]), array[4], array[5], array[6], array[7], array[8]);
                addRestaurant(newRestaurant);
            }
        }
        br.close();
    }

    public void readMenuFromFile(String fileName) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] array = line.split(",");
            Food newFood = new Food(Integer.parseInt(array[0]), array[1], array[2], Double.parseDouble(array[3]));
            addFood(newFood);
        }
        br.close();
    }

    public void addRestaurantsToFile(String fileName) throws Exception
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for(Restaurant restaurant : restaurantList){
        String line = restaurant.getId()+","+restaurant.getName()+","+restaurant.getScore()+","+restaurant.getPrice()+","+restaurant.getZipCode()+","+restaurant.getCategory1();
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

    public void addMenuToFile(String fileName) throws Exception
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for(var restaurant : restaurantList) {
            List<Food> menu = restaurant.getMenu();
            for (Food food : menu) {
                String line = food.getRestaurantId() + "," + food.getCategory() + "," + food.getName() + "," + food.getPrice();
                bw.write(line);
                bw.write(System.lineSeparator());
            }
        }
        bw.close();
    }
}
