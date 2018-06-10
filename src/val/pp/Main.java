package val.pp;

import com.sun.javafx.application.LauncherImpl;
import val.pp.preload.App;
import val.pp.preload.ppSplash;

public class Main {
    public static void main(String[] args) {
        LauncherImpl.launchApplication(App.class, ppSplash.class, args);
    }
}
