package val.pp.screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import val.pp.controller.dbController;
import val.pp.controller.msgDlgController;

import java.io.IOException;

public class App extends Application {
    public static Stage primaryStage;

    public static Stage initStageQuick(Stage owner, Parent screen, String title) {
        Stage stage = new Stage();
        stage.setScene(new Scene(screen));
        stage.setTitle(title);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        dbController dbConn = new dbController("", "");
        Parent screen_HomeScreen = FXMLLoader.load(getClass().getResource("/val/pp/views/MainScreen.fxml"));
        this.primaryStage = primaryStage;
        primaryStage.setScene(new Scene(screen_HomeScreen));
        primaryStage.setTitle("Project Planner");
        initPopups();
        primaryStage.show();
        ppSplash.splashScreen.hide();
    }

    private void initPopups() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/val/pp/views/messageDialog.fxml"));
            Parent newScreen = loader.load();
            msgDlgController mdc = loader.getController();
            Stage newStage = App.initStageQuick(App.primaryStage, newScreen, "NOTICE!");
            msgDlgController.thisCtrl = mdc;
            msgDlgController.stage = newStage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
