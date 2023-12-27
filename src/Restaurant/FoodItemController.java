package Restaurant;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.Food;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FoodItemController implements Initializable{
    
    @FXML
    private Label category;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(Food food){
        category.setText("Category: "+food.getCategory());
        name.setText(food.getName());
        price.setText("Price: "+food.getPrice());
    }
}
