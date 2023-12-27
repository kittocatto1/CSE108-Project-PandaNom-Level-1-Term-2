package Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DTO.Food;
import DTO.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FoodSearchController {
    private Main main;
    List <Restaurant> restaurantList = ReadThreadCustomer.getRestaurantList();
    private RestaurantDatabase RD = new RestaurantDatabase(restaurantList);
    int option = 0;

    @FXML
    private ImageView backArrow;

    @FXML
    private ButtonBar buttonBar;

    @FXML
    private MenuItem category;

    @FXML
    private MenuItem categoryInRestaurant;

    @FXML
    private MenuItem costliestFood;

    @FXML
    private MenuItem name;

    @FXML
    private MenuItem nameInRestaurant;

    @FXML
    private MenuItem price;

    @FXML
    private MenuItem priceInRestaurant;

    @FXML
    private Button reset;

    @FXML
    private VBox foodVBox;

    @FXML
    private Label result;

    @FXML
    private MenuButton searchOptions;

    @FXML
    private Button submit;

    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private MenuItem totalFood;

    @FXML
    void byCategory(ActionEvent event) {
        t1.setText(null);
        t2.setText(null);
        searchOptions.setText("Search by category");
        t2.setVisible(false);
        t1.setVisible(true);
        t1.setPromptText("Enter category");
        buttonBar.setVisible(true);
        option = 2;
    }

    @FXML
    void byName(ActionEvent event) {
        t1.setText(null);
        t2.setText(null);
        searchOptions.setText("Search by name");
        t2.setVisible(false);
        t1.setVisible(true);     
        t1.setPromptText("Enter food name");
        buttonBar.setVisible(true);
        option = 1;
    }


    @FXML
    void byPrice(ActionEvent event) {
        t1.setText(null);
        t2.setText(null);
        searchOptions.setText("Search by price");
        t1.setVisible(true);
        t2.setVisible(true);
        t2.setPromptText("Enter minimum price");
        t1.setPromptText("Enter maximum price");
        buttonBar.setVisible(true);
        option = 3;
    }


    @FXML
    void goHome(MouseEvent event) {
        try {
            main.showHomePage(ReadThreadCustomer.username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        t1.setText(null);
        t2.setText(null);
    }

    @FXML
    void submitAction(ActionEvent event) {
       switch (option) {
        case 1:{
            ArrayList<Food> foodFound = RD.searchFoodByName(t1.getText());
                if(foodFound.size()>0){
                    result.setVisible(true);
                    result.setTextFill(Color.GREENYELLOW);
                    result.setText(foodFound.size()+" search results");
                    showInfo(foodFound);
                }
                else
                {
                    result.setVisible(true);
                    result.setTextFill(Color.RED);
                    result.setText("No search result found");
                    foodVBox.getChildren().clear();
                }
            break;
        }
        case 2:{
            ArrayList<Food> foodFound = RD.searchFoodByCategory(t1.getText());
                if(foodFound.size()>0){
                    result.setVisible(true);
                    result.setTextFill(Color.GREENYELLOW);
                    result.setText(foodFound.size()+" search results");
                    showInfo(foodFound);
                }
                else
                {
                    result.setVisible(true);
                    result.setTextFill(Color.RED);
                    result.setText("No search result found");
                    foodVBox.getChildren().clear();
                }
            break;
        }
        case 3:{
            double min = 0, max = 0;
            try{
                min = Double.parseDouble(t2.getText());
                max = Double.parseDouble(t1.getText());}
            catch(NumberFormatException e){
                e.printStackTrace();
            }
            ArrayList<Food> foodFound = RD.searchFoodByPrice(min,max);
                if(foodFound.size()>0){
                    result.setVisible(true);
                    result.setTextFill(Color.GREENYELLOW);
                    result.setText(foodFound.size()+" search results");
                    showInfo(foodFound);
                }
                else
                {
                    result.setVisible(true);
                    result.setTextFill(Color.RED);
                    result.setText("No search result found");
                    foodVBox.getChildren().clear();
                }
            break;
        }

       }
    }

    @FXML
    void totalFoodAction(ActionEvent event) {
        result.setVisible(false);
        t1.setText(null);
        t2.setText(null);
        searchOptions.setText("Total foods in restaurants");
        t1.setVisible(false);
        t2.setVisible(false);
        buttonBar.setVisible(false);
        option = 8;
        HashMap<String,Integer> map = RD.getRestaurantsAndTotalFoodItems();
        showList(map);
    }

    void setMain(Main main) {
        this.main = main;
    }
    public void showInfo(ArrayList<Food> food) {
        foodVBox.getChildren().clear();
        for (int i = 0; i < food.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("foodItem.fxml"));
            try {
                VBox vbox = fxmlLoader.load();
                foodItemController fic = fxmlLoader.getController();
                fic.setData(food.get(i));
                foodVBox.getChildren().add(vbox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showList(HashMap<String,Integer> map){
        foodVBox.getChildren().clear();
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("totalFood.fxml"));
            try {
                VBox vbox = fxmlLoader.load();
                totalFoodController ric = fxmlLoader.getController();
                ric.setData(entry.getKey(),""+entry.getValue());
                foodVBox.getChildren().add(vbox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
