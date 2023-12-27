package Customer;

import java.io.IOException;

import DTO.SignupDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {
    private Main main ;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

     @FXML
    private Label warning;


    @FXML
    private PasswordField passwordText;

    @FXML
    private Button resetButton;

    @FXML
    private Button signUp;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField userText;

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    @FXML
    void showLogIn(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signUpAction(ActionEvent event) {
        String Name = userText.getText();
        String Password = passwordText.getText();
        if(Name.isEmpty() || Password.isEmpty()
        ){
            warning.setVisible(true);
            System.out.println("ERROR");
        }
        else
        {
            
            SignupDTO ob = new SignupDTO(Name, Password);
            try {
                main.getNetworkUtil().write(ob);
            } catch (IOException e) {
                e.printStackTrace();
            }
            main.showSignUpConfirmationAlert();
            
        }
    }

    void setMain(Main main) {
        this.main = main;
    }
}
