package val.pp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import val.pp.Model.Project;

import java.net.URL;
import java.util.ArrayList;
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
    public EventHandler<ActionEvent> fireEvent;
    public boolean done = false;

    public void btnSubmit(ActionEvent event) {
        fireEvent.handle(event);
        if (done) {
            Stage stage = (Stage) btnSubmit.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //doBindings()
        //lbProjects.setItems(obsListProj);
        //lbProjects.getSelectionModel().selectFirst();
    }
}
