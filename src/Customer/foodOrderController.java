package Customer;

import java.net.URL;

import javax.swing.text.html.ImageView;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import DTO.Food;
public class foodOrderController implements Initializable{
    Food food;
    private AnotherMenuController anotherMenuController;
    @FXML
    private Label Name;

    @FXML
    private Label Price;

    @FXML
    private ImageView addButton;

    @FXML
    private Label category;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setData(Food food){
       Name.setText(food.getName());
       Price.setText("$"+food.getPrice());
       category.setText(food.getCategory());
       this.food = food;
    }
    @FXML
    private Label count;

    @FXML
    void addToCart(MouseEvent event) {
        count.setVisible(true);
        int prev = food.getOrderCount();
        food.setOrderCount(prev+1);
        count.setText(""+(prev+1));
        anotherMenuController.updateOrderText(food.getName(), food.getOrderCount(), food.getPrice());
    }


    @FXML
    void removeFromCart(MouseEvent event) {
        int prev = food.getOrderCount();
        if(prev>0){
            food.setOrderCount(prev-1);
            count.setText(""+(prev-1));
            anotherMenuController.updateOrderText(food.getName(), food.getOrderCount(), food.getPrice());
        }
        if(prev-1==0){
            count.setVisible(false);
        }
    }

    public void setAnotherMenuController(AnotherMenuController anotherMenuController) {
        this.anotherMenuController = anotherMenuController;
    }
}
