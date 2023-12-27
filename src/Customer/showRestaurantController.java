package Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DTO.Restaurant;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class showRestaurantController implements Initializable{
    private Main main;
    @FXML
    private Label Restaurant;
    public void setData(String name,Main main){
        Restaurant.setText(name);
        this.main = main;
    }

    @FXML
    void showMenu(MouseEvent event) {
        String s = Restaurant.getText();
        System.out.println(s);
        List<Restaurant> restaurantList = ReadThreadCustomer.getRestaurantList();
        for(Restaurant res : restaurantList){
            if(res.getName().equals(s)){
                try {
                    res.setFoodCountToZero();
                    main.showMenuPage(res);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    void setMain(Main main) {
        this.main = main;
    }

}
