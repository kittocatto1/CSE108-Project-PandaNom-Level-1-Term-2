package Restaurant;

import DTO.Restaurant;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HomeController {
    private Main main;
    Restaurant restaurantInfo;
    @FXML
    private Label categories;

    @FXML
    private Label id1;

    @FXML
    private Button logout;

    @FXML
    private Button menu;

    @FXML
    private Label name;

    @FXML
    private Button order;

    @FXML
    private Label price;

    @FXML
    private Label score;

    @FXML
    private Label zipcode;

    public void init(Restaurant restaurant) {
        restaurantInfo = restaurant;
        System.out.println(restaurantInfo);
        name.setText(restaurant.getName()+"!");
        score.setText(""+restaurant.getScore());
        price.setText(restaurant.getPrice());
        zipcode.setText(restaurant.getZipCode());
        id1.setText(""+restaurant.getId());
        String category = "";
        category += restaurant.getCategory1();
        if(restaurant.getCategory2()!=null){
            category += (" ,"+ restaurant.getCategory2());
        }
        if(restaurant.getCategory3()!=null){
            category += (","+"\n"+restaurant.getCategory3());
        }
        categories.setText(category);
    }
    @FXML
    void logoutAction(ActionEvent event) {
        try {
            main.getNetworkUtil().closeConnection();
            Platform.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menuShow(ActionEvent event) {
        try {
            main.showMenuPage();
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
    void setMain(Main main) {
        this.main = main;
    }

    @FXML
    void totalSaleShow(ActionEvent event) {
        try {
            main.showSalesPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

