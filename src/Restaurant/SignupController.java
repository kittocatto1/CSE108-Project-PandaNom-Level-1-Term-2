package Restaurant;

import java.io.IOException;

import DTO.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignupController {
    private Main main;
    @FXML
    private TextField category1;

    @FXML
    private TextField category2;

    @FXML
    private TextField category3;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private TextField price;

    @FXML
    private Button resetButton;

    @FXML
    private TextField score;

    @FXML
    private Button signupButton;

    @FXML
    private TextField zipcode;

    @FXML
    private Label warning;

    @FXML
    void resetAction(ActionEvent event) {
        category1.setText(null);
        category2.setText(null);
        category3.setText(null);
        name.setText(null);
        id.setText(null);
        score.setText(null);
        price.setText(null);
        zipcode.setText(null);
        password.setText(null);
    }

    @FXML
    void signupAction(ActionEvent event) {
        String Name = name.getText();
        String Id = id.getText();
        String Score = score.getText();
        String Price = price.getText();
        String Zipcode = zipcode.getText();
        String Category1 = category1.getText();
        String Category2 = category2.getText();
        String Category3 = category3.getText();
        String Password = password.getText();
        if(Name.isEmpty() || Id.isEmpty() || 
        Score.isEmpty() || Price.isEmpty() || Zipcode.isEmpty()||
        Category1.isEmpty() || Password.isEmpty()
        ){
            warning.setVisible(true);

        }
        else
        {
            int ID =0;
            double score = 0;
            try{
                ID = Integer.parseInt(Id);
                score = Double.parseDouble(Score);
            }
            catch(NumberFormatException e){
                e.printStackTrace();
            }
            Restaurant r = new Restaurant(Password, ID, Name, score, Price, Zipcode, Category1, Category2, Category3);
            try {
                main.getNetworkUtil().write(r);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void goLogin(MouseEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void setMain(Main main) {
        this.main = main;
    }
}

