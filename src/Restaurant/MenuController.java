package Restaurant;
import java.util.List;

import DTO.Food;
import DTO.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class MenuController {
    private Main main;
    Restaurant restaurant;
    @FXML
    private ImageView back;
    @FXML
    private Label msg;
    @FXML
    private VBox menuVbox;

    @FXML
    void goHome(MouseEvent event) {
        restaurant = ReadThreadRestaurant.getRestaurant();
        try {
            main.showHomePage(restaurant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showInfo(List<Food> food) {
        menuVbox.getChildren().clear();
        for (int i = 0; i < food.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("foodItem.fxml"));
            try {
                VBox vbox = fxmlLoader.load();
                FoodItemController fic = fxmlLoader.getController();
                fic.setData(food.get(i));
                menuVbox.getChildren().add(vbox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    void setMain(Main main) {
        List <Food> menu = ReadThreadRestaurant.getRestaurant().getMenu();
        msg.setText("Total Food Items: "+menu.size());
        showInfo(menu);
        this.main = main;
    }
}
