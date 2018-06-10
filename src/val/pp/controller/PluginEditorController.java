package val.pp.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import val.pp.Model.Project;

import java.net.URL;
import java.util.ResourceBundle;

public class PluginEditorController implements Initializable {
    public TextField tfName;
    public TextArea taDesc;
    public TextField tfAuthorIdea;
    public DatePicker ideaDatePicker;
    public TextField tfAuthorPlugin;
    public DatePicker pluginDatePicker;
    public CheckBox cbEnabled;
    public TextArea taRequirements;
    public Button btnSubmit;
    public ChoiceBox<Project> lbProjects;

    //set via mainController!
    public ObservableList<Project> obsListProj;

    public void btnSubmit(ActionEvent event) {
        Stage stage = (Stage) btnSubmit.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //doBindings()
        lbProjects.setItems(obsListProj);
        lbProjects.getSelectionModel().selectFirst();
    }
}
