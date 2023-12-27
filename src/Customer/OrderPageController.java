package Customer;
import java.util.List;

import DTO.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class OrderPageController {
    private Main main;
    List <Restaurant> restaurantList = ReadThreadCustomer.getRestaurantList(); 
    @FXML
    private ImageView back;

    @FXML
    private VBox vbox;

    public void showInfo(List<Restaurant> restaurantList) {
            for (int i = 0; i < restaurantList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("showRestaurant.fxml"));
                try {
                    VBox restaurants = fxmlLoader.load();
                    showRestaurantController ric = fxmlLoader.getController();
                    String s = restaurantList.get(i).getName();
                    ric.setData(s,main);
                    vbox.getChildren().add(restaurants);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    @FXML
    void goHome(MouseEvent event) {
        try {
            main.showHomePage(ReadThreadCustomer.username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void setMain(Main main) {
        
        this.main = main;
        showInfo(restaurantList);
    }
}
