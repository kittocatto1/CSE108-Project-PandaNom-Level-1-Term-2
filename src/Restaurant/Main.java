package Restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.IOException;

import DTO.Restaurant;

public class Main extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;

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
        new ReadThreadRestaurant(this);
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
        Image img = new Image(Main.class.getResourceAsStream("paupau3.png"));
        stage.getIcons().add(img);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    public void showHomePage(Restaurant restaurant) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        controller.init(restaurant);
        controller.setMain(this);
       
        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    public void showSignUpPage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("signup.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SignupController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Sign up");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    public void showMenuPage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu.fxml"));
        Parent root = loader.load();

        // Loading the controller
        MenuController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Menu");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    public void showOrderPage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("order.fxml"));
        Parent root = loader.load();

        // Loading the controller
        OrderController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Menu");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    public void showSalesPage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sales.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SalesController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Menu");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ImageView icon = new ImageView("/Restaurant/cross.png");
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
    
  
    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }
}
