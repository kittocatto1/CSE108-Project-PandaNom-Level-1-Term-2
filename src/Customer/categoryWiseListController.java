package Customer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class categoryWiseListController implements Initializable{
    @FXML
    private Label Category;

    @FXML
    private Label Restaurants;

    public void setData(String category, String restaurants){
        Category.setText(category);
        Restaurants.setText(restaurants);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }
}
