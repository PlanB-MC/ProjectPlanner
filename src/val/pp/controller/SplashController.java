package val.pp.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable {

    public Label lblOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblOutput.setText("0.11a");
    }
}
