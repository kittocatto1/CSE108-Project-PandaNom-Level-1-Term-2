package Customer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.IOException;

import DTO.Restaurant;


public class Main extends Application {

    private Stage stage;
    NetworkUtil networkUtil;

    public Stage getStage() {
        return stage;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();
    }

    void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThreadCustomer(this);
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        Image img = new Image(Main.class.getResourceAsStream("pawpaw.png"));
        stage.getIcons().add(img);
        stage.setTitle("PANDANOM");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    public void showHomePage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        controller.init(userName);
        controller.setMain(this);

        // Set the primary stage
       
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    public void showMenuPage(Restaurant restaurant) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("anothermenu.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AnotherMenuController controller = loader.getController();
        controller.init(restaurant);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Order food");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    public void showRestaurantSearch() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("restaurantSearch.fxml"));
        Parent root = loader.load();
        // Loading the controller
        RestaurantSearchController controller = loader.getController();
        controller.result.setText("Ple");
        controller.setMain(this);
        // Set the primary stage
        stage.setTitle("Search Restaurant");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    public void showFoodSearch() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("foodSearch.fxml"));
        Parent root = loader.load();

        // Loading the controller
        FoodSearchController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Search Food");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }
    public void showOrderPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("orderpage.fxml"));
        Parent root = loader.load();

        // Loading the controller
        OrderPageController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Order");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }
    
    public void showSignUp() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("signup.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SignUpController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Order");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }
    
    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ImageView icon = new ImageView("/Customer/cross.png");
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
         alert.getDialogPane().setStyle(
                    "-fx-font-size: 14px; " +
                            "-fx-font-family: 'Calibri'; " +
                            "-fx-text-fill: #333; " +
                            "-fx-background-color:  #d70f64; " +
                            "-fx-border-color:  #d70f64; " +
                            "-fx-border-width: 3px; " +
                            "-fx-border-radius: 8px;"
            );
        alert.setTitle("LOGIN ERROR");
        alert.setHeaderText("Incorrect Password or Username!");
        alert.showAndWait();
    }

    public void showOrderConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ImageView icon = new ImageView("/Customer/accept.png");
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
        alert.getDialogPane().setStyle(
                    "-fx-font-size: 14px; " +
                            "-fx-font-family: 'Calibri'; " +
                            "-fx-text-fill: #333; " +
                            "-fx-background-color:  #d70f64; " +
                            "-fx-border-color:  #d70f64; " +
                            "-fx-border-width: 3px; " +
                            "-fx-border-radius: 8px;"
            );
        alert.setHeaderText("Great news! Your order is confirmed and on its way.(\u02F6\u1D54 \u1D55 \u1D54\u02F6)");
        alert.showAndWait();
        try {
            showOrderPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showSignUpConfirmationAlert() {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("NEW ACCOUNT CREATED!");
            alert.setHeaderText("Account creation was successful. \n Welcome to PANDANOM");
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/CSS/styles.css").toExternalForm());
            ImageView icon = new ImageView("/Customer/accept.png");
            icon.setFitHeight(48);
            icon.setFitWidth(48);
            alert.getDialogPane().setGraphic(icon);
            alert.showAndWait();
            try {
                showLoginPage();;
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void showOrderArrival(String restaurant,String username)
    {
            Alert alert;
                alert = new Alert(Alert.AlertType.INFORMATION);
                ImageView icon = new ImageView("/Customer/delivery.png");
                icon.setFitHeight(48);
                icon.setFitWidth(48);
                alert.getDialogPane().setGraphic(icon);
            // alert.setTitle("D");
            alert.setHeaderText("Hungry no more! "+username+",your order from "+restaurant+" is here.");
            alert.setTitle("ORDER ARRIVED!");
            // alert.setContentText(content);
            alert.getDialogPane().setStyle(
                    "-fx-font-size: 14px; " +
                            "-fx-font-family: 'Calibri'; " +
                            "-fx-text-fill: #333; " +
                            "-fx-background-color:  #d70f64; " +
                            "-fx-border-color:  #d70f64; " +
                            "-fx-border-width: 3px; " +
                            "-fx-border-radius: 8px;"
            );
    
            alert.showAndWait();
        
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}
