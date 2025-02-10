package farmhub.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import farmhub.dao.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends BaseController {
    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private void handleRegisterRedirect(ActionEvent event) {
        navigateTo("/views/register.fxml", event);
    }

    @FXML
    private void handleLoginButton(ActionEvent event) {
        emailErrorLabel.setText("");
        passwordErrorLabel.setText("");

        String email = emailField.getText();
        String password = passwordField.getText();

        if (!User.isEmailTaken(email)) {
            showAlert("Login Failed", "Email not found!", Alert.AlertType.ERROR);
            return;
        }

        boolean isValidUser = User.validateUser(email, password);
        if (isValidUser) {
            showAlert("Login Successful", "Welcome back!", Alert.AlertType.INFORMATION);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/dashboard/dashboard.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to load the dashboard.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Login Failed", "Password is incorrect!", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}