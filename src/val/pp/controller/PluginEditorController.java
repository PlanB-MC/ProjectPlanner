package val.pp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PluginEditorController implements Initializable {
    public TextField txtName;
    public TextArea txtDesc;
    public TextField txtIdeaAuthor;
    public DatePicker ideaDatePicker;
    public TextField txtPluginAuthor;
    public DatePicker pluginDatePicker;
    public CheckBox cbEnabled;
    public TextArea txtReq;
    public Button btnSubmit;

    public void btnSubmit(ActionEvent event) {
        Stage stage = (Stage) btnSubmit.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
