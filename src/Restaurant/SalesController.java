package Restaurant;

import java.util.HashMap;
import java.util.List;
import DTO.Food;
import DTO.OrderDTO;
import DTO.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SalesController {
    private Main main;
    private Restaurant restaurent = ReadThreadRestaurant.getRestaurant();
    private List<OrderDTO> orders = ReadThreadRestaurant.orderHistory;
    HashMap<Food,Integer> sales = new HashMap<>();
    double total ;
    @FXML
    private ImageView back;

    @FXML
    private Label revenue;

    @FXML 
    private VBox historyBox;

    @FXML
    void goHome(MouseEvent event) {
        try {
            main.showHomePage(restaurent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void showOrderHistory(){
         total = 0;
         if(orders.size()>0){
        for (int i = 0; i < orders.size(); i++) {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("salesItem.fxml"));
           try {
                VBox vbox = fxmlLoader.load();
                salesItemController ric = fxmlLoader.getController();
                total += ric.setDataHistory(orders.get(i));
                historyBox.getChildren().add(vbox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        revenue.setText("TOTAL REVENUE: $"+String.format("%.2f",total));
    }
    }
    void setMain(Main main) {
        this.main = main;
        showOrderHistory();
    }
}
