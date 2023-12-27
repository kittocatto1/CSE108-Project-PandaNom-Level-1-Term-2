package Customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import DTO.Food;
public class foodItemController implements Initializable{

    @FXML
    private Label Category;

    @FXML
    private Label Food;

    @FXML
    private Label Price;

    @FXML
    private Label resID;

    @FXML
    private Label resName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(Food food){
        Category.setText("Category: "+food.getCategory());
        Food.setText(food.getName());
        Price.setText("Price: $"+food.getPrice());
        resID.setText("Res. ID: "+food.getRestaurantId());
        resName.setText("Res. Name: "+food.getRestaurantName());
    }

}
