package Customer;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;


import DTO.Food;
import DTO.OrderDTO;
import DTO.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class AnotherMenuController {
    private Main main;
    ArrayList<Food> foodList;
    RestaurantDatabase RD = new RestaurantDatabase(ReadThreadCustomer.getRestaurantList());
    Restaurant restaurant;
    String orderText = "";
    double totalCost = 0;
    int option = 0;
    
    @FXML
    private ImageView backArrow;

    @FXML
    private MenuItem category;

    @FXML
    private MenuItem cheapest;

    @FXML
    private MenuItem costliestFood;

    @FXML
    private VBox menuVbox;

    @FXML
    private MenuItem name;

    @FXML
    private MenuItem price;

    @FXML
    private MenuButton searchOptions;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private Label orders;

    @FXML
    private Label total;

    @FXML
    private ImageView search;

    @FXML 
    private Label resName;

    public void init(Restaurant restaurant){
        //ArrayList conversion type safety
        this.restaurant =restaurant;
        resName.setText(restaurant.getName());
        foodList = (ArrayList<Food>)restaurant.getMenu();
        //showFood(foodList);
    }
    public void showFood(ArrayList <Food> foods){
        menuVbox.getChildren().clear();
        for(int i=0; i<foods.size();i++){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("foodOrder.fxml"));
            try {
                AnchorPane anc = fxmlLoader.load();
                foodOrderController ric = fxmlLoader.getController();
                ric.setData(foods.get(i));
                ric.setAnotherMenuController(this);
                menuVbox.getChildren().add(anc);
            } catch (Exception e) {
                e.printStackTrace();
            
            }
        }
    }
    public void updateOrderText(String foodName, int orderCount, double price) {
    
        StringBuilder ordersText = new StringBuilder();
        totalCost = 0;
        for (Food food : foodList) {
        int count = food.getOrderCount();
            if (count>0) {
            totalCost += (food.getPrice()*food.getOrderCount());
            if (ordersText.length() > 0) {
                ordersText.append("\n");
            }
            ordersText.append(food.getName());
            ordersText.append(" - $");
            ordersText.append(String.format("%.2f",count*food.getPrice()));
            ordersText.append(" (x");
            ordersText.append(count);
            ordersText.append(")");
        }
        
    }
        orders.setText(ordersText.toString());
        String s = "Total : $" + String.format("%.2f", totalCost);
        total.setText(s);
    }

    public void updateTotalText(double total){
        String totalCost = "Total : $" + total;
        this.total.setText(totalCost);
    }
    @FXML
    void byCategory(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
        searchOptions.setText("Search by category");
        textField2.setVisible(false);
        textField1.setVisible(true);
        textField1.setPromptText("Enter category");
        search.setVisible(true);
        option = 1;
    }

    @FXML
    void byName(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
        searchOptions.setText("Search by name");
        textField2.setVisible(false);
        textField1.setVisible(true);
        textField1.setPromptText("Enter name");
        search.setVisible(true);
        option = 2;
    }

    @FXML
    void byPrice(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
        searchOptions.setText("Search by price");
        textField2.setVisible(true);
        textField1.setVisible(true);
        textField1.setPromptText("Enter maximum price");
        textField2.setPromptText("Enter minimum price");
        search.setVisible(true);
        option = 3;

    }

    @FXML
    void cheapestAction(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
        searchOptions.setText("Cheapest food items");
        textField2.setVisible(false);
        textField1.setVisible(false);
        search.setVisible(false);
        option = 4;
        ArrayList<Food> foodFound = RD.cheapestFoodItemsInRestaurant(restaurant.getName(),1e-25);
            if(foodFound.size()>0){
                showFood(foodFound);
            }
    }

    @FXML
    void costliestAction(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
        searchOptions.setText("Costliest food items");
        textField2.setVisible(false);
        textField1.setVisible(false);
        search.setVisible(false);
        option = 5;
        ArrayList<Food> foodFound = RD.costliestFoodItemsInRestaurant(restaurant.getName());
            if(foodFound.size()>0){
                showFood(foodFound);
            }
    }

    @FXML
    void allAction(ActionEvent event) {
        textField1.setText(null);
        textField2.setText(null);
        searchOptions.setText("Costliest food items");
        textField2.setVisible(false);
        textField1.setVisible(false);
        search.setVisible(false);
        showFood(foodList);
    }

    @FXML
    void goBack(MouseEvent event) {
        total.setText("Total: $0");
        orders.setText("---");
        try {
            main.showOrderPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void confirmAction(ActionEvent event) {
        HashMap<Food,Integer> foodOrdered = new HashMap<>();
        for(Food food : foodList){
            int count = food.getOrderCount();
            if(count>0){
                foodOrdered.put(food, count);
                System.out.println(food);
            }
        }
        if(!foodOrdered.isEmpty()){
        OrderDTO order = new OrderDTO();
        order.setFoodCount(foodOrdered);
        order.setTimeSent(LocalDateTime.now());
        order.setUser(ReadThreadCustomer.username);
        order.setRestaurantName(restaurant.getName());
        total.setText("Total: $0");
        orders.setText("---");
        try {
            System.out.println(order);
            main.getNetworkUtil().write(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
           main.showOrderConfirmationAlert();
        }
        restaurant.setFoodCountToZero();
    }

    @FXML
    void searchEnter(MouseEvent event) {
        
        switch (option) {
        case 1:{
             ArrayList<Food> foodFound = RD.searchFoodByCategoryInARestaurant(textField1.getText(),restaurant.getName());
                if(foodFound.size()>0){
                    showFood(foodFound);
                }
            break;
        }
        case 2:{
            ArrayList<Food> foodFound = RD.searchFoodByNameInARestaurant(textField1.getText(),restaurant.getName());
                if(foodFound.size()>0){
                    showFood(foodFound);
                }
            break;
        }
        case 3:{
            double min=0, max = 0;
            try{
                min = Double.parseDouble(textField2.getText());
                max = Double.parseDouble(textField1.getText());
            }
            catch(NumberFormatException e){
                e.printStackTrace();
            }
            ArrayList<Food> foodFound = RD.searchFoodByPriceRangeInARestaurant(min, max,restaurant.getName());
                if(foodFound.size()>0){
                    showFood(foodFound);
                }
            break;
        }
            default:{
                System.out.println("Error");
                break;
            }
                
        }
    }

    void setMain(Main main) {
        this.main = main;
    }

}
