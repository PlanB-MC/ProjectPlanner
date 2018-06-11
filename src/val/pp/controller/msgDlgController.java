package val.pp.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    public Button btnCancel;
    public Label lblHeader;
    public Label lblBody;
    public EventHandler<ActionEvent> event;

    public static void showError(String header, String body) {
        thisCtrl.event = null;
        thisCtrl.btnCancel.setVisible(false);
        thisCtrl.lblHeader.setText(header);
        thisCtrl.lblBody.setText(body);
        stage.show();
    }

    public static void showError(String header, String body, EventHandler<ActionEvent> eventHandler) {
        thisCtrl.event = eventHandler;
        thisCtrl.btnCancel.setVisible(true);
        thisCtrl.lblHeader.setText(header);
        thisCtrl.lblBody.setText(body);
        stage.show();
    }

    public void onbtnOK(ActionEvent event) {
        //Stage stage = (Stage) btnOK.getScene().getWindow();
        if (thisCtrl.event != null)
            thisCtrl.event.handle(event);
        stage.hide();
    }

    public void onbtnCancel(ActionEvent event) {
        stage.hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


}
