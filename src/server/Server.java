package server;

import util.NetworkUtil;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DTO.*;

public class Server {

    private ServerSocket serverSocket;
    HashMap<String, String> customerMap;
    HashMap<String,String> restaurantMap;
    List<Restaurant>  restaurantList;
    HashMap<String,NetworkUtil> clientMap;

    Server() {
        clientMap = new HashMap<>();
        customerMap = new HashMap<>();
        restaurantMap = new HashMap<>();
        restaurantList = new ArrayList<>();
        try {
            FileOperation.readCustomerLoginInfo(customerMap);
            FileOperation.readRestaurantInfo(restaurantList, restaurantMap);
            FileOperation.readFoodInfo(restaurantList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(customerMap,restaurantMap,clientMap, restaurantList,networkUtil);
    }

    public static void main(String[] args) {
       new Server();

    }
}
