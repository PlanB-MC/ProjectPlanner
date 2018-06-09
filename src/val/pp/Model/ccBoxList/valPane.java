package val.pp.Model.ccBoxList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import val.pp.Model.Plugins;

import java.util.ArrayList;

public class valPane {
    private static final ObservableList<Plugins> plugins = FXCollections.observableArrayList(new ArrayList<>());

    public valPane(ListView curListView) {
        plugins.addAll();//TODO: Get stuff from db
        curListView.setItems(plugins);
        curListView.setCellFactory(param -> new Draggable(plugins));
        curListView.setPrefWidth(180);
    }

/*    private ArrayList<valBox> listBoxes;
    private final Pane pane;

    private final double xPos = 20;

    public valPane(Pane paneLeft) {
        pane = paneLeft;
        listBoxes = new ArrayList<>();
    }

    public void addBox(String name){
        valBox newBox = new valBox(xPos, getYinsertPoint(), name);
        listBoxes.add(newBox);
        pane.getChildren().add(newBox);
    }

    private double getYinsertPoint(){
        return listBoxes.size() == 0 ? 20 : listBoxes.get(listBoxes.size()-1).getLayoutY() + 25;
    }*/
}
