package val.pp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import val.pp.Main;
import val.pp.Model.Project;
import val.pp.Model.valBox;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    //CONTROLS
    public ListView<Project> listPlugins;
    public TextArea txtArea;
    public Pane paneRight;
    public Pane paneLeft;
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
        String desc = "Lorus Ispum sup sup. Soek jy n broodjie?";
        Project numOne = new Project("TEST", desc + "1");
        Project numTwo = new Project("IS IT?", desc + "2");
        Project numThree = new Project("BLEH", desc + "3");
        obsProjects.addAll(numOne, numTwo, numThree);

        valBox numbaOee = new valBox(20,20,"AWE");
        paneLeft.setBorder(new Border( new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        paneLeft.getChildren().add(numbaOee);

        try {
            Method meth = null;
            Method[] methods = Math.class.getMethods();
            for (Method cur: methods){
                System.out.println(cur.getName());
                if (cur.getName().equals("pow"))
                    meth = cur;
            }
            meth.setAccessible(true);
            double ans = (double) meth.invoke(null,2,3);
            System.out.println(ans);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


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
