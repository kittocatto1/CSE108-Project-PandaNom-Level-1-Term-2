package Restaurant;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import DTO.Food;
import DTO.OrderDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class salesItemController implements Initializable{
    private OrderDTO order = new OrderDTO();
    
    @FXML
    private Label name;

    @FXML
    private Label info;

    @FXML
    private Label time;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    

       public double setDataHistory(OrderDTO order){
        double total = 0;
        this.order = order;
        name.setText("Username: "+order.getUser());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm:ss");
        String formattedTime = order.getTimeSent().format(formatter);
        time.setText("Order Delivery Time: "+formattedTime);
        HashMap<Food,Integer> map = order.getFoodCount();
        String orderText = "";
        for (Map.Entry<Food, Integer> entry : map.entrySet()) {
            Food food = entry.getKey();
            Integer count = entry.getValue();
            total += (count* food.getPrice());
            orderText += (food.getName()+" - $"+String.format("%.2f",(count*food.getPrice()))+" (x"+count+")\n");
        }
        info.setText(orderText);
        return total;
    }

    
}