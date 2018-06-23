import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/60c83449-dd46-44df-9384-0713810ea1c3.jpeg")));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Cordon Grande");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
