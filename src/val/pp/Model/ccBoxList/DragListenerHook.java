package val.pp.Model.ccBoxList;

import javafx.scene.control.ListView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import val.pp.Model.Ideas;
import val.pp.Model.Plugins;
import val.pp.controller.MainController;
import val.pp.controller.msgDlgController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DragListenerHook {
    //private static final ObservableList<Plugins> plugins = FXCollections.observableArrayList(new ArrayList<>());
    private static MainController controller;
    private static Method mSwitcherP;
    private static Method mSwitcherI;
    private static Method mSaveToDBP;
    private static Method mSaveToDBI;

    public DragListenerHook(MainController mainController, Method switcherP, Method switcherI, Method saveToDBP, Method saveToDBI) {
        controller = mainController;
        switcherP.setAccessible(true);
        switcherI.setAccessible(true);
        saveToDBP.setAccessible(true);
        saveToDBI.setAccessible(true);
        mSwitcherP = switcherP;
        mSwitcherI = switcherI;
        mSaveToDBP = saveToDBP;
        mSaveToDBI = saveToDBI;
    }

    public static <G> void updateLists(G curPluginIdea, ListView curList) {
        try {
            int level = switch_on_ListViewID(curList.getId());
            if (curPluginIdea instanceof Plugins) {
                ((Plugins) curPluginIdea).setLevel(level);
                mSwitcherP.invoke(controller, level, curPluginIdea);
                mSaveToDBP.invoke(controller, curPluginIdea);
            } else if (curPluginIdea instanceof Ideas) {
                ((Ideas) curPluginIdea).setAccepted((level - 6) == 1);
                mSwitcherI.invoke(controller, curPluginIdea);
                mSaveToDBI.invoke(controller, curPluginIdea);
            }
        } catch (IllegalAccessException e) {
            System.out.println("Oopsie1");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("Oopsie2");
            e.printStackTrace();
        }
    }

    private static int switch_on_ListViewID(String curListID) throws IllegalArgumentException {
        switch (curListID) {
            case "listReleased": {
                return 5;
            }
            case "listFixes": {
                return 4;
            }
            case "listDev": {
                return 3;
            }
            case "listQue": {
                return 2;
            }
            case "listFeas": {
                return 1;
            }
            case "listPurpose": {
                return 0;
            }
            case "listIdeaA": {
                return 7;
            }
            case "listIdeaP": {
                return 6;
            }
        }
        throw new IllegalArgumentException();
    }

    public <G> void hook(ListView<G>... listViews) {
        for (ListView<G> curList : listViews) {
            curList.setCellFactory(param -> new Draggable(curList.getItems()));

            curList.setOnDragOver(event -> {
                if (event.getGestureSource() != curList && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            });

            curList.setOnDragDropped(event -> {
                if (IllegalDragAndDrop(curList, event)) return;
                if (curList.getItems().size() == 0) {
                    ListView<G> curListPlugins = ((Draggable) event.getGestureSource()).getListView();
                    G curPlugin = curListPlugins.getSelectionModel().getSelectedItem();
                    // curList.getItems().add(curPlugin);
                    curListPlugins.getSelectionModel().select(-1);
                    curListPlugins.getItems().remove(curPlugin);
                    updateLists(curPlugin, curList);
                }

            });
        }
    }

    public static <G> boolean IllegalDragAndDrop(ListView<G> curList, DragEvent event) {
        try {
            int indexCur = switch_on_ListViewID(curList.getId());
            int indexSource = switch_on_ListViewID(((Draggable) event.getGestureSource()).getListView().getId());
            if ((indexCur - 6) < 0) {
                if ((indexSource - 6) >= 0) {
                    msgDlgController.showError("DND Exception!","You cannot do that...");
                    return true;
                }
            } else if ((indexSource - 6) <= 0) {
                msgDlgController.showError("DND Exception!","You cannot do that...");
                return true;
            }
        } catch (IllegalArgumentException e) {
            msgDlgController.showError("DND Exception!","You cannot do that...");
            e.printStackTrace();
            return true;
        }
        return false;
    }

}
