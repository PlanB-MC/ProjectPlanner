package val.pp.Model.ccBoxList;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import val.pp.Model.Plugins;

import java.util.ArrayList;
import java.util.List;

class Draggable extends ListCell<Plugins> {
    //private final String curString;
    private static ListView<Plugins> sourceListView;
    private static int draggedIndex;
    private final ObservableList<Plugins> mainList;

    public Draggable(ObservableList<Plugins> mainList) {
        this.mainList = mainList;
        ListCell thisCell = this;

        //setAlignment(Pos.CENTER);

        setOnDragDetected(event -> {
            if (getItem() == null) {
                return;
            }

            ObservableList<Plugins> items = getListView().getItems();

            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();

            content.putString(String.valueOf(mainList.indexOf(getItem())));

            dragboard.setContent(content);
            sourceListView = getListView();
            event.consume();
        });

        setOnDragOver(event -> {
            if (event.getGestureSource() != thisCell &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        setOnDragEntered(event -> {
            if (event.getGestureSource() != thisCell &&
                    event.getDragboard().hasString()) {
                setOpacity(0.3);
            }
        });

        setOnDragExited(event -> {
            if (event.getGestureSource() != thisCell &&
                    event.getDragboard().hasString()) {
                setOpacity(1);
            }
        });

        setOnDragDropped(event -> {
            if (getItem() == null && sourceListView.getItems() == mainList) {
                return;
            }

            Dragboard db = event.getDragboard();
            boolean success = false;
            draggedIndex = -1;

            if (db.hasString()) {
                ObservableList<Plugins> items = getListView().getItems();
                int draggedIdx = Integer.parseInt(db.getString());
                if (sourceListView.getItems() == mainList) {
                    int thisIdx = items.indexOf(getItem());
                    Plugins temp = mainList.get(draggedIdx);
                    mainList.set(draggedIdx, mainList.get(thisIdx));
                    items.set(thisIdx, temp);
                    List<Plugins> itemscopy = new ArrayList(getListView().getItems());
                    getListView().getItems().setAll(itemscopy);
                    success = true;
                } else {
                    Plugins curPlugin = sourceListView.getItems().get(draggedIdx);
                    //mainList.add(curPlugin);
                    sourceListView.getSelectionModel().select(-1);
                    sourceListView.getItems().remove(draggedIdx);
                    DragListenerHook.updateLists(curPlugin, mainList.get(0).getLevel());//Assumes size >= 1
                    success = true;
                }
            }
            event.setDropCompleted(success);

            event.consume();
        });

        setOnDragDone(Event::consume);
    }

    @Override
    protected void updateItem(Plugins item, boolean empty) {
        super.updateItem(item, empty);
        /*if (empty || item == null) {
            setGraphic(null);
        } else {*/
            if (item != null) {
                setText(item.getName());
            } else {
                setText("");
            }
       // }
    }
}