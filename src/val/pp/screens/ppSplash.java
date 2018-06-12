package val.pp.screens;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        ImageView splash = new ImageView("/val/pp/resources/PlanB_Splash.jpg");
        Scene scene = new Scene(new StackPane(splash), 789, 306);
        return scene;
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        //ignore
    }

}