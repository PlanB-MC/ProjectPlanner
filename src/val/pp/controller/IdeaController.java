package val.pp.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class IdeaController implements Initializable {
    public Button btnSubmit;
    public TextField tfName;
    public TextArea taDesc;
    public DatePicker datePicker;

    public EventHandler<ActionEvent> fireEvent;
    public boolean done = false;

    public void setBtnSubmit(ActionEvent event) {
        fireEvent.handle(event);
        if (done) {
            Stage stage = (Stage) btnSubmit.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
