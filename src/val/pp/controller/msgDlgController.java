package val.pp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class msgDlgController implements Initializable {
    public static msgDlgController thisCtrl;
    public static Stage stage;
    public Button btnOK;
    public Label lblHeader;
    public Label lblBody;

    public static void showError(String header, String body) {
        thisCtrl.lblHeader.setText(header);
        thisCtrl.lblBody.setText(body);
        stage.show();
    }

    public void onbtnOK(ActionEvent event) {
        //Stage stage = (Stage) btnOK.getScene().getWindow();
        stage.hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
