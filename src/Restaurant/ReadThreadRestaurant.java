package Restaurant;

import javafx.application.Platform;
import DTO.*;
import java.util.ArrayList;
import java.util.List;

public class ReadThreadRestaurant implements Runnable {
    private final Thread thr;
    private final Main main;
    static Restaurant restaurantInfo;
    static List<OrderDTO> ordersConfirmed;
    static List<OrderDTO> orderHistory;

    public ReadThreadRestaurant(Main main) {
        ordersConfirmed = new ArrayList<>();
        orderHistory = new ArrayList<>();
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }
    
    static Restaurant getRestaurant(){
        return restaurantInfo;
    }
    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if(o instanceof Restaurant){
                        Restaurant r = (Restaurant) o;
                        restaurantInfo = r;
                        System.out.println(restaurantInfo);
                         Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                    try {
                                        main.showHomePage(r);
                                    
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                    }
                    else if(o instanceof String){
                        String line = (String) o;
                        if(line.equals("Wrong Password")){
                            Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                    try {
                                        main.showAlert();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        else if(line.contains("Restaurant added")){
                            String [] arr = line.split(",");
                            System.out.println("Print from res"+arr[1]);
                            if(arr[1].equals("true")){
                            Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                    try {
                                       // main.showRestaurantAddConfirmation();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            }
                            else if(arr[1].equals("false")){
                            Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                    try {
                                       // main.showRestaurantAddError();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            }
                        }
                    }
                    else if(o instanceof OrderDTO){
                            OrderDTO order = (OrderDTO) o;
                            ordersConfirmed.add(order);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                    try {
                                        main.showOrderPage();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
         finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



