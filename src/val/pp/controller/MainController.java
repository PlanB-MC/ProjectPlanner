package val.pp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import val.pp.Main;
import val.pp.Model.Plugins;
import val.pp.Model.Project;
import val.pp.Model.ccBoxList.valBox;
import val.pp.Model.ccBoxList.valPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    //CONTROLS
    public ListView<Project> listPlugins;
    public TextArea txtArea;
    public ListView<Plugins> listReleased;
    public ListView<Plugins> listFixes;
    public ListView<Plugins> listDev;
    public ListView<Plugins> listQue;
    public ListView<Plugins> listFeas;
    public ListView<Plugins> listPurpose;
    public ListView<Plugins> listIdeaA;
    public ListView<Plugins> listIdeaP;
    private ArrayList<Project> arrProjects = new ArrayList<>();
    private ObservableList<Project> obsProjects = FXCollections.observableArrayList(arrProjects);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TEST();
        doBindings();
    }

    private void doBindings() {
        listPlugins.setItems(obsProjects);

        listPlugins.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                txtArea.textProperty().unbindBidirectional(oldValue.descProperty());
            }
            if (newValue != null) {
                System.out.println(newValue.descProperty());
                txtArea.textProperty().bindBidirectional(newValue.descProperty());
            }
        });

        listPlugins.getSelectionModel().selectedIndexProperty();

        txtArea.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshPluginList();
        });
    }

    private void refreshPluginList() {
        int index = listPlugins.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            listPlugins.fireEvent(new ListView.EditEvent<>(
                            listPlugins, ListView.editCommitEvent(), obsProjects.get(index), index
                    )
            );
        }
        listPlugins.getSelectionModel().select(index);
    }

    private void TEST() {
        //SAMPLES
        /*String desc = "Lorus Ispum sup sup. Soek jy n broodjie?";
        Project numOne = new Project("TEST", desc + "1");
        Project numTwo = new Project("IS IT?", desc + "2");
        Project numThree = new Project("BLEH", desc + "3");
        obsProjects.addAll(numOne, numTwo, numThree);*/

       /* valBox numbaOee = new valBox(20, 20, "AWE");
        valBox qwe = new valBox(20, 50, "AWE");
        valBox numdgsbaOee = new valBox(20, 70, "AWE");
        valBox numfhbaOee = new valBox(20, 100, "AWE");
        paneLeft.getChildren().addAll(numbaOee, qwe, numdgsbaOee, numfhbaOee);*/


       // valPane vp = new valPane(paneLeft);
    }

    public void addPlugin(ActionEvent actionEvent) {
        try {
            //TODO: move to MAIN. avoid extra imports
            Parent newScreen = FXMLLoader.load(getClass().getResource("/val/pp/views/PluginScreen.fxml"));
            Stage newStage = Main.initStageQuick(Main.primaryStage, newScreen, "Plugin Information");
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
