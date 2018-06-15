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
    private static boolean isVisible = false;
    private static boolean onQue = false;

    public static void showError(String header, String body) {
        thisCtrl.event = null;
        thisCtrl.btnCancel.setVisible(false);
        thisCtrl.lblHeader.setText(header);
        thisCtrl.lblBody.setText(body);
        if (isVisible) onQue = true;
        isVisible = true;
        stage.show();
    }

    public static void showError(String header, String body, EventHandler<ActionEvent> eventHandler) {
        thisCtrl.event = eventHandler;
        thisCtrl.btnCancel.setVisible(true);
        thisCtrl.lblHeader.setText(header);
        thisCtrl.lblBody.setText(body);
        if (isVisible) onQue = true;
        isVisible = true;
        stage.show();
        msgDlgController.runOnQue();
    }

    public void onbtnOK(ActionEvent event) {
        //Stage stage = (Stage) btnOK.getScene().getWindow();
        if (thisCtrl.event != null)
            thisCtrl.event.handle(event);
        isVisible = false;
        stage.hide();
        msgDlgController.runOnQue();
    }

    public static void runOnQue(){
        if (onQue) {
            stage.show();
            onQue = false;
        }
    }

    public void onbtnCancel(ActionEvent event) {
        isVisible = true;
        stage.hide();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
