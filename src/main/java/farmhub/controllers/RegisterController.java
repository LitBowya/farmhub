package farmhub.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import farmhub.dao.User;
import java.io.IOException;

public class RegisterController extends BaseController {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private Label emailErrorLabel;

    @FXML
    private Label passwordErrorLabel;

    @FXML
    private void handleLoginRedirect(ActionEvent event) {
        navigateTo("/views/account/login.fxml", event);
    }

    @FXML
    private void handleRegisterButton(ActionEvent event) {
        emailErrorLabel.setText("");
        passwordErrorLabel.setText("");

        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            showAlert("Registration Failed", "Passwords do not match!", Alert.AlertType.ERROR);
            return;
        }

        if (User.isEmailTaken(email)) {
            showAlert("Registration Failed", "Email is already taken!", Alert.AlertType.ERROR);
            return;
        }

        boolean isRegistered = User.registerUser(username, email, password);
        if (isRegistered) {
            showAlert("Registration Successful", "You have successfully registered!", Alert.AlertType.INFORMATION);
            navigateToLogin(event);
        } else {
            showAlert("Registration Failed", "An error occurred. Please try again later.", Alert.AlertType.ERROR);
        }
    }

    private void navigateToLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/account/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the login page.", Alert.AlertType.ERROR);
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
