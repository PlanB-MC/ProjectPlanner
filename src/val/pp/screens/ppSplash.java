package val.pp.screens;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ppSplash extends Preloader {

    public static Stage splashScreen;

    @Override
    public void start(Stage stage) throws Exception {
        splashScreen = stage;
        splashScreen.setScene(createScene());
        splashScreen.setResizable(false);
        splashScreen.initStyle(StageStyle.UNDECORATED);
        splashScreen.show();
    }

    public Scene createScene() {
        /*ImageView splash = new ImageView("/val/pp/resources/PlanB_Splash.png");
        Scene scene = new Scene(new StackPane(splash), 789, 306);*/
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/val/pp/views/SplashScreen.fxml"));
            return new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        //ignore
    }

}