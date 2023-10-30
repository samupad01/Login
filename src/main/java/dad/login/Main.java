package dad.login;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginController controller = new LoginController();

        // Creamos una escena utilizando la vista obtenida desde el LoginController.
        Scene scene = new Scene(controller.getView().getView());

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}




