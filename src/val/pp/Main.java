package val.pp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import val.pp.controller.MainController;
import val.pp.controller.dbController;

public class Main extends Application {
    public static Stage primaryStage;
    private Parent screen_HomeScreen;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dbController dbConn = new dbController("","");
        Parent screen_HomeScreen = FXMLLoader.load(getClass().getResource("views/MainScreen.fxml"));
        this.primaryStage = primaryStage;
        primaryStage.setScene(new Scene(screen_HomeScreen));
        primaryStage.setTitle("Project Planner");
        //primaryStage.initStyle(StageStyle.UTILITY);
       // primaryStage.initModality(Modality.APPLICATION_MODAL);

       // Parent screen_Login = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
       // Parent screen_PluginInfo = FXMLLoader.load(getClass().getResource("views/PluginScreen.fxml"));
       // Stage sgLogin = initStageQuick(primaryStage,screen_Login, "Login");
       // Stage sgPInfo = initStageQuick(primaryStage,screen_Login, "Plugin Information");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static Stage initStageQuick(Stage owner, Parent screen, String title){
        Stage stage = new Stage();
        stage.setScene(new Scene(screen));
        stage.setTitle(title);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        return stage;
    }
}
