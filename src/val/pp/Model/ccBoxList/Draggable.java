package val.pp.Model.ccBoxList;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.input.*;
import val.pp.Model.Plugins;

import java.util.ArrayList;
import java.util.List;

class Draggable extends ListCell<Plugins> {
    //private final String curString;
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
            content.put(new DataFormat("1997"), getItem());//TODO: could be erroneous
            dragboard.setContent(content);

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
            if (getItem() == null) {
                return;
            }

            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                ObservableList<Plugins> items = getListView().getItems();
                int draggedIdx = items.indexOf(db.getString());
                int thisIdx = items.indexOf(getItem());

                Plugins temp = mainList.get(draggedIdx);
                mainList.set(draggedIdx, mainList.get(thisIdx));
                mainList.set(draggedIdx, getItem());

                items.set(thisIdx, temp);
                //items.set(thisIdx, db.getString());

                List<Plugins> itemscopy = new ArrayList(getListView().getItems());
                getListView().getItems().setAll(itemscopy);

                success = true;
            }
            event.setDropCompleted(success);

            event.consume();
        });

        setOnDragDone(DragEvent::consume);
    }

    @Override
    protected void updateItem(Plugins item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            setText(item.getName());
        }
    }
}