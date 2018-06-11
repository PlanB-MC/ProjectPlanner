package val.pp.Model.ccBoxList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import val.pp.Model.Plugins;

import java.util.ArrayList;

public class DragListenerHook {
    //private static final ObservableList<Plugins> plugins = FXCollections.observableArrayList(new ArrayList<>());

    public DragListenerHook(ListView curListView) {
        //plugins.addAll();
        //curListView.setItems(plugins);
        curListView.setCellFactory(param -> new Draggable(curListView.getItems()));
        curListView.setPrefWidth(180);
    }
}
