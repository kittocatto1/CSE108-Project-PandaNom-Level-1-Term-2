package Customer;

import java.net.URL;
import java.util.ResourceBundle;

import DTO.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class restaurantItemController implements Initializable{
    @FXML
    private Label ID;

    @FXML
    private Label Name;

    @FXML
    private Label Price;

    @FXML
    private Label Score;

    @FXML
    private Label ZipCode;

    @FXML
    private Label Categories;

    public void setData(Restaurant restaurant){
        ID.setText(" "+restaurant.getId());
        Name.setText(" "+restaurant.getName());
        Score.setText(" Score: "+restaurant.getScore());
        Price.setText(" Price: "+restaurant.getPrice());
        ZipCode.setText(" Zip code: "+restaurant.getZipCode());
        String categories = restaurant.getCategory1();
        if(restaurant.getCategory2()!=null)
        {
            categories += (", "+restaurant.getCategory2());
        }
        if(restaurant.getCategory3()!=null)
        {
            categories += (", "+restaurant.getCategory3());
        }
        Categories.setText(" Categories: "+categories);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
