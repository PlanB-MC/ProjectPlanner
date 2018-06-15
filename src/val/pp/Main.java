package val.pp;

import com.sun.javafx.application.LauncherImpl;
import val.pp.controller.dbController;
import val.pp.controller.msgDlgController;
import val.pp.screens.App;
import val.pp.screens.ppSplash;

import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        LauncherImpl.launchApplication(App.class, ppSplash.class, args);
        dbController.timer.cancel();
    }
}
