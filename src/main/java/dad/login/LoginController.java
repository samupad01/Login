package dad.login;

import dad.login.auth.AuthService;
import dad.login.auth.LdapAuthService;
import dad.login.auth.FileAuthService;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {

    private LoginModel model;
    private LoginView view;
    private AuthService authService;

    public LoginController() {
        model = new LoginModel();
        view = new LoginView();

        //Listeners para actualizar el modelo en función de los cambios en la vista
        view.getUserField().textProperty().addListener((obs, oldText, newText) -> {
            model.setUsername(newText);
        });

        view.getPasswordField().textProperty().addListener((obs, oldText, newText) -> {
            model.setPassword(newText);
        });

        view.getLdapCheckBox().selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            model.setUseLdap(isSelected);
        });

        // Evento del botón de acceso
        view.getLoginButton().setOnAction(e -> login());

        // Evento del botón de cancelar
        view.getCancelButton().setOnAction(e -> System.exit(0));
    }

    private void login() {
        boolean isAuthenticated = false;

        if (model.isUseLdap()) {
            authService = new LdapAuthService();
        } else {
            authService = new FileAuthService();
        }

        try {
            isAuthenticated = authService.login(model.getUsername(), model.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Error", "Ha ocurrido un error durante la autenticación.");
            return;
        }

        if (isAuthenticated) {
            showAlert(AlertType.INFORMATION, "Acceso permitido", "Las credenciales de acceso son válidas.");
            System.exit(0); // o puedes redirigir al usuario a otra ventana/pantalla
        } else {
            showAlert(AlertType.ERROR, "Acceso denegado", "El usuario y/o la contraseña no son válidos.");
            view.getPasswordField().clear();
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void show(Stage primaryStage) {
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(view.getView(), 300, 200));
        primaryStage.show();
    }

    public LoginView getView() {
        return view;
    }

}



