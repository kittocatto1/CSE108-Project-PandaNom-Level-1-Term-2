package Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import DTO.LoginDTO;

import java.io.IOException;


public class LoginController {

    private Main main;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button signUp;

    @FXML
    private Label label1;

    @FXML
    private Label label2;


    @FXML
    void loginAction(ActionEvent event) {
        
        String userName = userText.getText();
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        loginDTO.setClientType("Customer");
        try {
            main.getNetworkUtil().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    @FXML
    void showSignUp(ActionEvent event) {
        try {
           main.showSignUp();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void setMain(Main main) {
        this.main = main;
    }
}

