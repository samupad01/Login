package dad.login;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginModel {

    private StringProperty username;
    private StringProperty password;
    private BooleanProperty useLdap;

    public LoginModel() {
        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        useLdap = new SimpleBooleanProperty();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public BooleanProperty useLdapProperty() {
        return useLdap;
    }

    public boolean isUseLdap() {
        return useLdap.get();
    }

    public void setUseLdap(boolean useLdap) {
        this.useLdap.set(useLdap);
    }
}

