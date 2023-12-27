package server;

import DTO.*;
import util.NetworkUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    HashMap<String, String> customerMap;
    HashMap<String,String> restaurantMap;
    HashMap<String,NetworkUtil> clientMap;
    List <Restaurant> restaurantList;
    

    public ReadThreadServer(HashMap<String, String> customerMap,HashMap<String,String> restaurantMap,HashMap<String,NetworkUtil> clientMap, List<Restaurant> restaurantList, NetworkUtil networkUtil) {
        this.customerMap = customerMap;
        this.restaurantMap = restaurantMap;
        this.clientMap = clientMap;
        this.networkUtil = networkUtil;
        this.restaurantList = restaurantList;
        this.thr = new Thread(this);
        thr.start();
    }

    public synchronized void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = "";
                        if(loginDTO.getClientType().equals("Customer")){
                            //System.out.println("Yes in customer");
                            password = customerMap.get(loginDTO.getUserName());
                            loginDTO.setStatus(loginDTO.getPassword().equals(password));
                            networkUtil.write(loginDTO);
                        }
                        else if(loginDTO.getClientType().equals("Restaurant")){
                            password = restaurantMap.get(loginDTO.getUserName());
                            loginDTO.setStatus(loginDTO.getPassword().equals(password));
                            if(loginDTO.isStatus()){
                                clientMap.put(loginDTO.getUserName(), networkUtil);
                                for(Restaurant restaurant : restaurantList){
                                if(restaurant.getName().equals(loginDTO.getUserName())){
                                    networkUtil.write(restaurant);
                                    break;
                                }
                            }
                            }
                            else{
                                networkUtil.write("Wrong Password");
                            }
                            }
                        }
                    }
                    if(o instanceof Restaurant){
                        Restaurant r = (Restaurant) o;
                        for(Restaurant a : restaurantList){
                            System.out.println(a);
                        }
                        boolean added = FileOperation.addRestaurant(r, restaurantList);
                        System.out.println(added);
                        if(added){
                        FileOperation.addRestaurantsToFile(restaurantList);
                        FileOperation.readRestaurantInfo(restaurantList, restaurantMap);
                        System.out.println(clientMap.isEmpty());
                        for (String key : clientMap.keySet()) {
                            NetworkUtil connected = clientMap.get(key);
                            System.out.println("Key: " + key + ", Value: " + restaurantList);
                            connected.write(restaurantList);
                        }
                    }
                    networkUtil.write("Restaurant added"+","+added);
                    }
                    if(o instanceof String){
                        String s = (String) o;
                        System.out.println(s);
                       
                        if(s.contains("Load Restaurant List")){
                            String [] arr = s.split(",");
                            clientMap.put(arr[1], networkUtil);
                            networkUtil.write(restaurantList);
                        }
                        else if(s.contains("Confirm")){
                            String [] arr = s.split(",");
                            String username = arr[1];
                            String restaurant = arr[2];
                            for (String key : clientMap.keySet()) {
                                if(key.equals(username)){
                                NetworkUtil connected = clientMap.get(key);
                                connected.write("Order arrived,"+restaurant);
                                }
                            }
                        }
                    }
                    if(o instanceof OrderDTO){
                        OrderDTO order = (OrderDTO) o;
                        String restaurantName = order.getRestaurantName();
                        for (String key : clientMap.keySet()) {
                            if(key.equals(restaurantName)){
                            NetworkUtil connected = clientMap.get(key);
                            connected.write(order);
                            }
                        }
                        System.out.println(order);
                    }
                    if(o instanceof SignupDTO){
                        SignupDTO ob = (SignupDTO) o;
                        String name = ob.getUserName();
                        String password = ob.getPassword();
                        customerMap.put(name, password);
                        FileOperation.addCustomer(name,password, customerMap);
                    }
                }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



