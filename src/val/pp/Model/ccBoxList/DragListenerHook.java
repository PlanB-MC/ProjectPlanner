package val.pp.Model.ccBoxList;

import javafx.scene.control.ListView;
import javafx.scene.input.TransferMode;
import val.pp.Model.Plugins;
import val.pp.controller.MainController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DragListenerHook {
    //private static final ObservableList<Plugins> plugins = FXCollections.observableArrayList(new ArrayList<>());
    private static MainController controller;
    private static Method mSwitcher;
    private static Method mSaveToDB;

    public DragListenerHook(MainController mainController, Method switcher, Method saveToDB) {
        controller = mainController;
        switcher.setAccessible(true);
        saveToDB.setAccessible(true);
        mSwitcher = switcher;
        mSaveToDB = saveToDB;
    }

    public static void updateLists(Plugins curPlugin, int newLevel) {
        try {
            curPlugin.setLevel(newLevel);
            mSwitcher.invoke(controller, curPlugin.getLevel(), curPlugin);
            mSaveToDB.invoke(controller, curPlugin);
        } catch (IllegalAccessException e) {
            System.out.println("Oopsie1");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("Oopsie2");
            e.printStackTrace();
        }
    }

    public void hook(ListView<Plugins>... listViews) {

        for (ListView<Plugins> curList : listViews) {
            curList.setCellFactory(param -> new Draggable(curList.getItems()));

            curList.setOnDragOver(event -> {
                ListView<Plugins> curListPlugins = ((Draggable) event.getGestureSource()).getListView();
                if (event.getGestureSource() != curList && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            });

            curList.setOnDragDropped(event -> {
                if (curList.getItems().size() == 0) {
                    ListView<Plugins> curListPlugins = ((Draggable) event.getGestureSource()).getListView();
                    Plugins curPlugin = curListPlugins.getSelectionModel().getSelectedItem();
                    // curList.getItems().add(curPlugin);
                    curListPlugins.getSelectionModel().select(-1);
                    curListPlugins.getItems().remove(curPlugin);
                    updateLists(curPlugin, switch_on_ListViewID(curList.getId()));
                }

            });
        }
    }

    private int switch_on_ListViewID(String curListID) {
        switch (curListID){
            case "listReleased":{
                return 5;
            }
            case "listFixes":{
                return 4;
            }
            case "listDev":{
                return 3;
            }
            case "listQue":{
                return 2;
            }
            case "listFeas":{
                return 1;
            }
            case "listPurpose":{
                return 0;
            }
        }
        throw new IllegalArgumentException();
    }

}
