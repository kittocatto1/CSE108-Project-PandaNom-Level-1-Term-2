package Customer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class HomeController {

    private Main main;

    @FXML
    private Label username;

    @FXML
    private ImageView image;

    @FXML
    private Button button;

    @FXML
    private Button cart;

    @FXML
    private Button searchByFoodButton;

    @FXML
    private Button searchByRestaurantButton;

    @FXML
    private Button Order;

    public void init(String msg) {
        username.setText(msg);
        // Image img = new Image(CustomerMain.class.getResourceAsStream("1.png"));
        // image.setImage(img);
    }

    @FXML
    void logoutAction(ActionEvent event) {
        try {
            Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(Main main) {
        this.main = main;
    }

    @FXML
    void searchByRestaurantAction(ActionEvent event) {
        try {
            main.showRestaurantSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchByFoodAction(ActionEvent event){
        try {
            main.showFoodSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void orderAction(ActionEvent event) {
        try {
            main.showOrderPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    //color 
    
}
