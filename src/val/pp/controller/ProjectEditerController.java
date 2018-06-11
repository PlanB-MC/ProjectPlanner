package val.pp.controller;

import javafx.event.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectEditerController implements Initializable {
    public Button btnSubmit;
    public Button btnClear;
    public TextField tfName;
    public TextField tfOwner;
    public TextField tfServer;
    public TextArea taDesc;
    public EventHandler<ActionEvent> fireEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setBtnSubmit(ActionEvent actionEvent) {
        fireEvent.handle(actionEvent);
        Stage stage = (Stage) btnSubmit.getScene().getWindow();
        stage.close();
    }

    public void setBtnClear(ActionEvent actionEvent) {
        tfName.setText("");
        tfOwner.setText("");
        tfServer.setText("");
        taDesc.setText("");
    }
}
