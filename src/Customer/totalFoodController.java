package Customer;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class totalFoodController implements Initializable{

    @FXML
    private Label Count;

    @FXML
    private Label Restaurant;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    public void setData(String restaurant, String count){
        Restaurant.setText(" "+restaurant);
        Count.setText(" "+count);
    }
}

