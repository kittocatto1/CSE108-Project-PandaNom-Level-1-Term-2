package Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

public class RestaurantSearchController {

    private Main main;
    List <Restaurant> restaurantList = ReadThreadCustomer.getRestaurantList();
    private RestaurantDatabase RD = new RestaurantDatabase(restaurantList);
    int option;
    @FXML
    private ButtonBar buttonBar;

    @FXML
    private MenuItem categoryOption;

    @FXML
    private Label label1;

    @FXML
    private MenuItem nameOption;

    @FXML
    private MenuItem priceOption;

    @FXML
    private MenuItem categoryWise;

    @FXML
    private Button reset;

    @FXML
    private MenuItem scoreOption;

    @FXML
    private MenuButton searchOptions;

    @FXML
    private Button submit;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private MenuItem zipCodeOption;

    @FXML
    private Label restaurantNotFound;

    @FXML
    Label result;

    @FXML
    private ImageView backArrow;

    @FXML
    private VBox restaurantVbox;

    @FXML
    void goHome(MouseEvent event) {
        try {
            main.showHomePage(ReadThreadCustomer.username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchByNameShow(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
        searchOptions.setText("Search by name");
        textField2.setVisible(false);
        textField1.setVisible(true);
        textField1.setPromptText("Enter name");
        buttonBar.setVisible(true);
        option = 1;
    }

    @FXML
    void searchByScoreShow(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
        searchOptions.setText("Search by score");
        textField2.setVisible(true);
        textField2.setPromptText("Enter minimum score");
        textField1.setVisible(true);
        textField1.setPromptText("Enter maximum score");
        buttonBar.setVisible(true);
        option = 2;
    }

    @FXML
    void searchByCategoryShow(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
        searchOptions.setText("Search by category");
        textField2.setVisible(false);
        textField1.setVisible(true);
        textField1.setPromptText("Enter category");
        buttonBar.setVisible(true);
        option = 3;
    }

    @FXML
    void searchByPriceShow(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
        searchOptions.setText("Search by price");
        textField2.setVisible(false);
        textField1.setVisible(true);
        textField1.setPromptText("Enter price");
        buttonBar.setVisible(true);
        option = 4;
    }

    @FXML
    void searchByZipCodeShow(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
        searchOptions.setText("Search by zip code");
        textField2.setVisible(false);
        textField1.setVisible(true);
        textField1.setPromptText("Enter zip code");
        buttonBar.setVisible(true);
        option = 5;
    }

    @FXML
    void categoryWiseShow(ActionEvent event) {
        result.setVisible(false);
        textField1.setText(null);
        textField2.setText(null);
        textField1.setVisible(false);
        textField2.setVisible(false);
        searchOptions.setText("Category wise list");
        buttonBar.setVisible(false);
            Set<String> distinctCategories = RD.getDistinctCategories();
            HashMap<String,String> map = new HashMap<>();
            for(String category : distinctCategories)
            {
                String printCategory = " ";
                String[] subStrings =  category.split(" ");
                for(String s : subStrings)
                {
                    if(s.equalsIgnoreCase("and")){
                            s.toLowerCase();
                        }
                        else {
                        String original = s;
                        String firstChar = original.substring(0, 1).toUpperCase();
                        s = firstChar + original.substring(1);
                        }
                        printCategory += (s + " ");
                        }
                        ArrayList<Restaurant>restaurantsFound = RD.searchRestaurantByCategory(category);
                        boolean notFirst = false;
                        String restaurants =" ";
                        for(Restaurant restaurant: restaurantsFound){
                        if(notFirst)
                        {
                            restaurants += (", " + restaurant.getName());
                        }
                        else
                        {
                             restaurants += restaurant.getName();
                             notFirst = true;
                        }
                        }
                        map.put(printCategory, restaurants);
                    }
                    showList(map);
    }

    @FXML
    void resetAction(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
    }

    @FXML
    void submitAction(ActionEvent event) {
        String input;
        if(option==1){
            input = textField1.getText();
             ArrayList<Restaurant> restaurantsFound = RD.searchRestaurantByName(input);
                if(restaurantsFound.size()>0){
                    result.setVisible(true);
                    result.setTextFill(Color.GREENYELLOW);
                    result.setText(restaurantsFound.size()+" search results");
                    showInfo(restaurantsFound);
            }
            else
            {
                result.setVisible(true);
                result.setTextFill(Color.RED);
                result.setText("No search result found");
                restaurantVbox.getChildren().clear();
            }
        }
        else if(option==2){
            double min = Double.parseDouble(textField2.getText());
            double max = Double.parseDouble(textField1.getText());
            System.out.println(min+" "+max);
             ArrayList<Restaurant> restaurantsFound = RD.searchRestaurantByScore(min,max);
                if(restaurantsFound.size()>0){
                    result.setVisible(true);
                    result.setTextFill(Color.GREENYELLOW);
                    result.setText(restaurantsFound.size()+" search results");
                    showInfo(restaurantsFound);
            }
            else
            {
                result.setVisible(true);
                result.setTextFill(Color.RED);
                result.setText("No search result found");
                restaurantVbox.getChildren().clear();
            }
        }
        else if(option==3){
            input = textField1.getText();
             ArrayList<Restaurant> restaurantsFound = RD.searchRestaurantByCategory(input);
                if(restaurantsFound.size()>0){
                    result.setVisible(true);
                    result.setTextFill(Color.GREENYELLOW);
                    result.setText(restaurantsFound.size()+" search results");
                    showInfo(restaurantsFound);
            }
            else
            {
                result.setVisible(true);
                result.setTextFill(Color.RED);
                result.setText("No search result found");
                restaurantVbox.getChildren().clear();
            }
        }
        else if(option==4){
            input = textField1.getText();
             ArrayList<Restaurant> restaurantsFound = RD.searchRestaurantByPrice(input);
                if(restaurantsFound.size()>0){
                    result.setVisible(true);
                    result.setTextFill(Color.GREENYELLOW);
                    result.setText(restaurantsFound.size()+" search results");
                    showInfo(restaurantsFound);
            }
            else
            {
                result.setVisible(true);
                result.setTextFill(Color.RED);
                result.setText("No search result found");
                restaurantVbox.getChildren().clear();
            }
        }
        else if(option==5){
             input = textField1.getText();
             ArrayList<Restaurant> restaurantsFound = RD.searchRestaurantByZipCode(input);
                if(restaurantsFound.size()>0){
                    result.setVisible(true);
                    result.setTextFill(Color.GREENYELLOW);
                    result.setText(restaurantsFound.size()+" search results");
                    showInfo(restaurantsFound);
            }
            else
            {
                result.setVisible(true);
                result.setTextFill(Color.RED);
                result.setText("No search result found");
                restaurantVbox.getChildren().clear();
            }
        }
    }

    void setMain(Main main) {
        this.main = main;
    }

    // URL location, ResourceBundle resources
    public void showInfo(ArrayList<Restaurant> restaurants) {
        restaurantVbox.getChildren().clear();
        for (int i = 0; i < restaurants.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("restaurantItem.fxml"));
            try {
                VBox vbox = fxmlLoader.load();
                restaurantItemController ric = fxmlLoader.getController();
                ric.setData(restaurants.get(i));
                restaurantVbox.getChildren().add(vbox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showList(HashMap<String,String> map){
        restaurantVbox.getChildren().clear();
        for(Map.Entry<String, String> entry : map.entrySet()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryWiseList.fxml"));
            try {
                VBox vbox = fxmlLoader.load();
                categoryWiseListController ric = fxmlLoader.getController();
                ric.setData(entry.getKey(),entry.getValue());
                restaurantVbox.getChildren().add(vbox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
