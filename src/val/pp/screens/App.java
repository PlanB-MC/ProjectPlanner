package val.pp.screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import val.pp.controller.dbController;
import val.pp.controller.msgDlgController;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class App extends Application {
    public static Stage primaryStage;
    private static String screenName;

    public static Stage initStageQuick(Stage owner, Parent screen, String title) {
        Stage stage = new Stage();
        stage.setScene(new Scene(screen));
        stage.setTitle(title);
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner == null ? primaryStage : owner);
        return stage;
    }

    public static void toggleScreen() {
        //new File("/val/pp/views/MainScreen.fxml")
        //MainScreen
        //Matts_Screen
        if (screenName.equals("MainScreen")) screenName = "Matts_Screen";
        else if (screenName.equals("Matts_Screen")) screenName = "MainScreen";
        try {
            System.out.println("val/pp/views/" + screenName + ".fxml");
            Parent screen_HomeScreen = FXMLLoader.load(App.class.getResource("/val/pp/views/" + screenName + ".fxml"));
            primaryStage.setScene(new Scene(screen_HomeScreen));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("/val/pp/resources/pblogo.png")));
        dbController dbConn = new dbController("", "");
        this.primaryStage = primaryStage;
        initPopups();
        System.setOut(new PrintStream(System.out) {
            public void println(String s) {
                msgDlgController.showError("This should not happen O__o", "SQL ERROR!");
                super.println(s);
            }
            // override some other methods?
        });
        Parent screen_HomeScreen = FXMLLoader.load(getClass().getResource("/val/pp/views/" + loadScreen() + ".fxml"));
        primaryStage.setScene(new Scene(screen_HomeScreen));
        primaryStage.setTitle("Project Planner");
        primaryStage.show();
        ppSplash.splashScreen.hide();
    }

    private void setupMainScreen() throws Exception {

    }

    private String loadScreen() {
        try {
            InputStream is = getClass().getResourceAsStream("/val/pp/resources/config.txt");
            Scanner scanner = new Scanner(is);
            //String line = scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.charAt(0) == '#') continue;
                is.close();
                screenName = line;
                return line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.exit(-1);
        return "";
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
