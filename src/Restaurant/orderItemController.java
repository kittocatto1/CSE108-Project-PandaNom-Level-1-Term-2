package Restaurant;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import DTO.Food;
import DTO.OrderDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class orderItemController implements Initializable{
    private OrderController orderController;
    private OrderDTO order = new OrderDTO();
    private String username;
    @FXML
    private Label name;

    @FXML
    private Label info;

    @FXML
    private Label time;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    @FXML
    void acceptOrder(MouseEvent event) {
        try {
            orderController.getMain().getNetworkUtil().write("Confirm," + username+","+order.getRestaurantName());
            orderController.removeOrder(order);
            order.setTimeSent(LocalDateTime.now());
            ReadThreadRestaurant.orderHistory.add(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setData(OrderDTO order){
        this.order = order;
        username = order.getUser();
        name.setText("Username: "+order.getUser());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm:ss");
        String formattedTime = order.getTimeSent().format(formatter);
        time.setText("Order Arrival Time: "+formattedTime);
        HashMap<Food,Integer> map = order.getFoodCount();
        String orderText = "";
        for (Map.Entry<Food, Integer> entry : map.entrySet()) {
            Food food = entry.getKey();
            Integer count = entry.getValue();
            orderText += (food.getName()+" - $"+String.format("%.2f",(count*food.getPrice()))+" (x"+count+")\n");
        }
        info.setText(orderText);
    }

    public void setDataHistory(OrderDTO order){
        this.order = order;
        username = order.getUser();
        name.setText("Username: "+order.getUser());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = order.getTimeSent().format(formatter);
        time.setText("Order Delivery Time: "+formattedTime);
        HashMap<Food,Integer> map = order.getFoodCount();
        String orderText = "";
        for (Map.Entry<Food, Integer> entry : map.entrySet()) {
            Food food = entry.getKey();
            Integer count = entry.getValue();
            orderText += (food.getName()+" - $"+String.format("%.2f",(count*food.getPrice()))+" (x"+count+")\n");
        }
        info.setText(orderText);
    }
    void setOrderController(OrderController orderController){
        this.orderController = orderController;
    }
    
}