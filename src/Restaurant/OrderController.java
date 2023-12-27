package Restaurant;

import java.util.List;

import DTO.OrderDTO;
import DTO.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class OrderController {
    private Main main;
    List <OrderDTO> foods = ReadThreadRestaurant.ordersConfirmed;
    @FXML
    private ImageView back;

    @FXML
    private VBox orderBox;

    @FXML
    void goHome(MouseEvent event) {
        Restaurant restaurant = ReadThreadRestaurant.getRestaurant();
        try {
            main.showHomePage(restaurant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void showOrder(){
       
        if(foods.size()>0){
        for (int i = 0; i < foods.size(); i++) {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orderItem.fxml"));
           try {
                VBox vbox = fxmlLoader.load();
                orderItemController ric = fxmlLoader.getController();
                ric.setData(foods.get(i));
                ric.setOrderController(this);
                orderBox.getChildren().add(vbox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    }
    
    @FXML
    void acceptAll(ActionEvent event) {
        foods.clear();
        orderBox.getChildren().clear();
    }

    
    void setMain(Main main) {
        this.main = main;
        showOrder();
    }

    Main getMain(){
        return main;
    }
    public void removeOrder(OrderDTO order) {
        foods.remove(order);
        refreshOrderPage(); 
    }
    
    public void refreshOrderPage() {
        orderBox.getChildren().clear();
        showOrder();
    }
}
