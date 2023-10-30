package dad.login;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class LoginView {

    private VBox view;
    private TextField userField;
    private PasswordField passwordField;
    private CheckBox ldapCheckBox;
    private Button loginButton, cancelButton;

    public LoginView() {
        userField = new TextField();
        userField.setPromptText("Nombre de usuario");

        passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña del usuario");

        ldapCheckBox = new CheckBox("Usar LDAP");

        loginButton = new Button("Acceder");
        cancelButton = new Button("Cancelar");

        GridPane gridPane = new GridPane();
        gridPane.addRow(0, new Label("Usuario:"), userField);
        gridPane.addRow(1, new Label("Contraseña:"), passwordField);
        gridPane.addRow(2, ldapCheckBox);
        gridPane.addRow(3, loginButton, cancelButton);
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        view = new VBox(15, gridPane);
        view.setAlignment(Pos.CENTER);
    }

    public VBox getView() {
        return view;
    }

    public TextField getUserField() {
        return userField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public CheckBox getLdapCheckBox() {
        return ldapCheckBox;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }
}

