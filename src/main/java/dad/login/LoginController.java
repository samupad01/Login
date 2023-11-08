package dad.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.login.auth.AuthService;
import dad.login.auth.LdapAuthService;
import dad.login.auth.FileAuthService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements Initializable {

    @FXML
    private VBox view;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox ldapCheckBox;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    private LoginModel model = new LoginModel(); // Inicialización en línea
    private AuthService authService;

    public LoginController() {
        // Carga la vista desde el archivo FXML
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userField.textProperty().bindBidirectional(model.usernameProperty());
        passwordField.textProperty().bindBidirectional(model.passwordProperty());
        ldapCheckBox.selectedProperty().bindBidirectional(model.useLdapProperty());

        loginButton.setOnAction(e -> login());
        cancelButton.setOnAction(e -> System.exit(0));
    }

    public VBox getView() {
        return view;
    }

    private void login() {
        boolean isAuthenticated = false;

        // Decidir qué servicio de autenticación usar
        if (model.isUseLdap()) {
            authService = new LdapAuthService();
        } else {
            authService = new FileAuthService();
        }

        // Intentar autenticar al usuario
        try {
            isAuthenticated = authService.login(model.getUsername(), model.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Ha ocurrido un error durante la autenticación.");
            return;
        }

        // Procesar el resultado de la autenticación
        if (isAuthenticated) {
            showAlert(AlertType.INFORMATION, "Acceso permitido", "Las credenciales de acceso son válidas.");
            // Aquí debes redirigir al usuario a la siguiente pantalla o cerrar la sesión.
        } else {
            showAlert(AlertType.ERROR, "Acceso denegado", "El usuario y/o la contraseña no son válidos.");
            passwordField.clear();
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}





