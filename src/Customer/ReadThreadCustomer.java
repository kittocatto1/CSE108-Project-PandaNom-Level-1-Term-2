package Customer;

import javafx.application.Platform;
import DTO.LoginDTO;
import DTO.Restaurant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadThreadCustomer implements Runnable {
    private final Thread thr;
    private final Main main;
    static List<Restaurant> restaurantList;
    static String username;

    public ReadThreadCustomer(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        main.getNetworkUtil().write((String)("Load Restaurant List,"+loginDTO.getUserName()));
                                        username = loginDTO.getUserName();
                                        main.showHomePage(loginDTO.getUserName());
                                
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    main.showAlert();
                                }

                            }
                        });
                    }
                    else if(o instanceof ArrayList){
                        List<Restaurant> restaurants =  (ArrayList<Restaurant>)o;
                        restaurantList = restaurants;

                    }
                    else if(o instanceof String){
                        String s = (String) o;
                        System.out.println(s);
                        if(s.contains("Order arrived"))
                        {
                            String arr[] = s.split(",");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                        try {
                                            main.showOrderArrival(arr[1],username);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static List<Restaurant> getRestaurantList()
    {
        return restaurantList;
    }
}



