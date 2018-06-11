package val.pp;

import com.sun.javafx.application.LauncherImpl;
import val.pp.screens.App;
import val.pp.screens.ppSplash;

public class Main {
    public static void main(String[] args) {
        LauncherImpl.launchApplication(App.class, ppSplash.class, args);
    }
}
