package val.pp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Pair;
import val.pp.Model.Ideas;
import val.pp.Model.Plugins;
import val.pp.Model.Project;
import val.pp.Model.ccBoxList.DragListenerHook;
import val.pp.screens.App;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

//TODO: Any edit button: doesnt update the ListViews
public class MainController implements Initializable {
    //CONTROLS
    public TextArea txtProjectInfo;
    public TextArea txtPluginInfo;
    public ListView<Project> listProjects;
    public ListView<Plugins> listReleased;
    public ListView<Plugins> listFixes;
    public ListView<Plugins> listDev;
    public ListView<Plugins> listQue;
    public ListView<Plugins> listFeas;
    public ListView<Plugins> listPurpose;
    public ListView<Ideas> listIdeaA;
    public ListView<Ideas> listIdeaP;
    public Button btnAddProj;
    public Button btnEditProj;
    public Button btnDelProj;
    public Button btnAddIdea;
    public Button btnEditIdea;
    public Button btnAddPlugin;
    public Button btnEditPlugin;
    public Button btnDelPlugin;
    public ChoiceBox<Plugins> choicePlugins;
    public ChoiceBox<Ideas> choiceIdeas;
    private Pair<Integer, ListView> curList = new Pair<>(-1, null);
    private ObservableList<Project> obsListProjects = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListReleased = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListFixes = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListDev = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListQue = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListFeas = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListPurpose = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Ideas> obsListIdeaA = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Ideas> obsListIdeaP = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListChoicePlugins = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Ideas> getObsListChoiceIdeas = FXCollections.observableArrayList(new ArrayList<>());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLoadFromDB();
        doBindings();
        refreshProjectList();
        refreshPluginList();
        populateChoicePlugins();
        populateChoiceIdeas();
        setDragganles();
        changeTXTinfo(null, listProjects.getSelectionModel().getSelectedItem());//Best solution i can find...
    }

    //<editor-fold desc="Bindings">
    private void doBindings() {
        //<editor-fold desc="Plugins">
        listReleased.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) listReleased.getSelectionModel().select(-1);
        });
        listFixes.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) listFixes.getSelectionModel().select(-1);
        });
        listDev.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) listDev.getSelectionModel().select(-1);
        });
        listQue.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) listQue.getSelectionModel().select(-1);
        });
        listFeas.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) listFeas.getSelectionModel().select(-1);
        });
        listPurpose.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) listPurpose.getSelectionModel().select(-1);
        });

        listReleased.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (listReleased.getSelectionModel().getSelectedIndex() != -1)
                setCur(listReleased);
            changeTXTinfo(oldValue, newValue);
        });
        listFixes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (listFixes.getSelectionModel().getSelectedIndex() != -1)
                setCur(listFixes);
            changeTXTinfo(oldValue, newValue);
        });
        listDev.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (listDev.getSelectionModel().getSelectedIndex() != -1)
                setCur(listDev);
            changeTXTinfo(oldValue, newValue);
        });
        listQue.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (listQue.getSelectionModel().getSelectedIndex() != -1)
                setCur(listQue);
            changeTXTinfo(oldValue, newValue);
        });
        listFeas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (listFeas.getSelectionModel().getSelectedIndex() != -1)
                setCur(listFeas);
            changeTXTinfo(oldValue, newValue);
        });
        listPurpose.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (listPurpose.getSelectionModel().getSelectedIndex() != -1)
                setCur(listPurpose);
            changeTXTinfo(oldValue, newValue);
        });

        listReleased.getSelectionModel().selectedIndexProperty();
        listFixes.getSelectionModel().selectedIndexProperty();
        listDev.getSelectionModel().selectedIndexProperty();
        listQue.getSelectionModel().selectedIndexProperty();
        listFeas.getSelectionModel().selectedIndexProperty();
        listPurpose.getSelectionModel().selectedIndexProperty();
        //</editor-fold>

        //<editor-fold desc="Ideas">
        listIdeaA.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) listIdeaA.getSelectionModel().select(-1);
        });
        listIdeaP.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) listIdeaP.getSelectionModel().select(-1);
        });

        listIdeaA.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (listIdeaA.getSelectionModel().getSelectedIndex() != -1)
                setCur(listIdeaA);
            changeTXTinfo(oldValue, newValue);
        });
        listIdeaP.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (listIdeaP.getSelectionModel().getSelectedIndex() != -1)
                setCur(listIdeaP);
            changeTXTinfo(oldValue, newValue);
        });

        listIdeaA.getSelectionModel().selectedIndexProperty();
        listIdeaP.getSelectionModel().selectedIndexProperty();
        //</editor-fold>

        //<editor-fold desc="Projects">
        listProjects.getSelectionModel().selectedIndexProperty();

        listProjects.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            changeTXTinfo(oldValue, newValue);
            loadFromDB_PluginIdeas(listProjects.getSelectionModel().getSelectedItem().getID());
            populateChoicePlugins();
            populateChoiceIdeas();
        });
        //</editor-fold>

        //<editor-fold desc="ChoiceBoxes">
        choicePlugins.getSelectionModel().selectedIndexProperty();

        choicePlugins.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) addPluginToProject(newValue);
                }
        );

        choiceIdeas.getSelectionModel().selectedIndexProperty();

        choiceIdeas.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) addIdeaToProject(newValue);
                }
        );
        //</editor-fold>

        //<editor-fold desc="TextAreas">
        txtProjectInfo.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshProjectList();
        });

        txtPluginInfo.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshPluginList();
        });
        //</editor-fold>
    }

    private void setDragganles() {
        DragListenerHook newHook1 = new DragListenerHook(listPurpose);
        DragListenerHook newHook2 = new DragListenerHook(listFeas);
    }

    private void setCur(ListView listReleased) {
        curList = new Pair<>(listReleased.getSelectionModel().getSelectedIndex(), listReleased);
        System.out.println(curList);
    }

    //<editor-fold desc="update TextAreas">
    private void changeTXTinfo(Project oldValue, Project newValue) {
        //TODO: make project info display
        if (oldValue != null) {
            txtProjectInfo.textProperty().unbind();
        }
        if (newValue != null) {
            //System.out.println(newValue.descProperty());
            txtProjectInfo.textProperty().bind(newValue.descProperty());
        }
    }

    private void changeTXTinfo(Ideas oldValue, Ideas newValue) {
        //TODO: make idea info display
        if (oldValue != null) {
            txtPluginInfo.textProperty().unbind();
        }
        if (newValue != null) {
            //System.out.println(newValue.descProperty());
            txtPluginInfo.textProperty().bind(newValue.descProperty());
        }
    }

    private void changeTXTinfo(Plugins oldValue, Plugins newValue) {
        //TODO: make plugin info display
        if (oldValue != null) {
            txtPluginInfo.textProperty().unbind();
        }
        if (newValue != null) {
            //System.out.println(newValue.descProperty());
            txtPluginInfo.textProperty().bind(newValue.nameProperty());
        }
    }
    //</editor-fold>

    private void refreshProjectList() {
        int index = listProjects.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            listProjects.fireEvent(new ListView.EditEvent<>(
                            listProjects, ListView.editCommitEvent(), obsListProjects.get(index), index
                    )
            );
        }
        listProjects.getSelectionModel().select(index);
    }

    private void refreshPluginList() {
        //Plugins
        int indexLR = listReleased.getSelectionModel().getSelectedIndex();
        int indexLFX = listFixes.getSelectionModel().getSelectedIndex();
        int indexLD = listDev.getSelectionModel().getSelectedIndex();
        int indexLQ = listQue.getSelectionModel().getSelectedIndex();
        int indexLF = listFeas.getSelectionModel().getSelectedIndex();
        int indexLP = listPurpose.getSelectionModel().getSelectedIndex();

        if (curList.getValue() == listReleased && indexLR >= 0) {
            listReleased.fireEvent(new ListView.EditEvent<>(
                            listReleased, ListView.editCommitEvent(), obsListReleased.get(indexLR), indexLR
                    )
            );
        }
        if (curList.getValue() == listFixes && indexLFX >= 0) {
            listFixes.fireEvent(new ListView.EditEvent<>(
                            listFixes, ListView.editCommitEvent(), obsListFixes.get(indexLFX), indexLFX
                    )
            );
        }
        if (curList.getValue() == listDev && indexLD >= 0) {
            listDev.fireEvent(new ListView.EditEvent<>(
                            listDev, ListView.editCommitEvent(), obsListDev.get(indexLD), indexLD
                    )
            );
        }
        if (curList.getValue() == listQue && indexLQ >= 0) {
            listQue.fireEvent(new ListView.EditEvent<>(
                            listQue, ListView.editCommitEvent(), obsListQue.get(indexLQ), indexLQ
                    )
            );
        }
        if (curList.getValue() == listFeas && indexLF >= 0) {
            listFeas.fireEvent(new ListView.EditEvent<>(
                            listFeas, ListView.editCommitEvent(), obsListFeas.get(indexLF), indexLF
                    )
            );
        }
        if (curList.getValue() == listPurpose && indexLP >= 0) {
            listPurpose.fireEvent(new ListView.EditEvent<>(
                            listPurpose, ListView.editCommitEvent(), obsListPurpose.get(indexLP), indexLP
                    )
            );
        }

        listReleased.getSelectionModel().select(indexLR);
        listFixes.getSelectionModel().select(indexLFX);
        listDev.getSelectionModel().select(indexLD);
        listQue.getSelectionModel().select(indexLQ);
        listFeas.getSelectionModel().select(indexLF);
        listPurpose.getSelectionModel().select(indexLP);

        //Ideas
        int indexIA = listIdeaA.getSelectionModel().getSelectedIndex();
        int indexIP = listIdeaP.getSelectionModel().getSelectedIndex();

        if (curList.getValue() == listIdeaA && indexIA >= 0) {
            listIdeaA.fireEvent(new ListView.EditEvent<>(
                            listIdeaA, ListView.editCommitEvent(), obsListIdeaA.get(indexIA), indexIA
                    )
            );
        }
        if (curList.getValue() == listIdeaP && indexIP >= 0) {
            listIdeaP.fireEvent(new ListView.EditEvent<>(
                            listIdeaP, ListView.editCommitEvent(), obsListIdeaP.get(indexIP), indexIP
                    )
            );
        }

        listIdeaA.getSelectionModel().select(indexIA);
        listIdeaP.getSelectionModel().select(indexIP);
    }

    //</editor-fold>

    //<editor-fold desc="Database Stuff">
    private void initLoadFromDB() {
        String sql = "";
        ResultSet resultset = null;
        try {
            //TODO: Load project
            sql = "SELECT * FROM Projects";
            resultset = dbController.executeQuery(sql);
            while (resultset.next()) {
                int id = Integer.parseInt(resultset.getString("pId"));
                String name = resultset.getString("pName");
                String desc = resultset.getString("pDesc");
                String server = resultset.getString("pServer");
                String owner = resultset.getString("pOwner");
                Project newProj = new Project(name, desc, server, owner, id);
                obsListProjects.add(newProj);
            }
            listProjects.setItems(obsListProjects);
            listProjects.getSelectionModel().selectFirst();
        } catch (SQLException e) {
            System.out.println("unable to do sql for: " + sql);
            e.printStackTrace();
            return;
        }
        loadFromDB_PluginIdeas(listProjects.getSelectionModel().getSelectedItem().getID());
    }

    private void loadFromDB_PluginIdeas(int pId) {
        obsListIdeaA.clear();
        obsListIdeaP.clear();
        obsListReleased.clear();
        obsListFixes.clear();
        obsListDev.clear();
        obsListQue.clear();
        obsListFeas.clear();
        obsListPurpose.clear();
        txtPluginInfo.clear();

        String sql = "";
        ResultSet resultset = null;
        try {
            //TODO: Load First Plugin
            sql = "SELECT * FROM Plugins, ProjectPlugins WHERE projectID = " + pId + " AND pId = pluginID";
            resultset = dbController.executeQuery(sql);
            while (resultset.next()) {
                int id = resultset.getInt("pId");
                String name = resultset.getString("pName");
                String desc = resultset.getString("pDesc");
                String ideaAuthor = resultset.getString("pIdeaAuthor");
                String pluginAuthor = resultset.getString("pPluginAuthor");
                String ideaDate = resultset.getString("pIdeaDate");
                String pluginDate = resultset.getString("pPluginDate");
                Boolean enabledByDefault = resultset.getBoolean("pEnabledByDefault");
                String requirements = resultset.getString("pRequirements");
                String todo = resultset.getString("pTODO");
                int level = resultset.getInt("pLevel");
                Plugins newPlugin = new Plugins(
                        name,
                        desc,
                        ideaAuthor,
                        ideaDate,
                        enabledByDefault,
                        requirements,
                        id,
                        pluginAuthor,
                        pluginDate,
                        level
                );
                switch_Level_on_Plugin(level, newPlugin);
            }
            listPurpose.setItems(obsListPurpose);
            listFeas.setItems(obsListFeas);
            listQue.setItems(obsListQue);
            listDev.setItems(obsListDev);
            listFixes.setItems(obsListFixes);
            listReleased.setItems(obsListReleased);
        } catch (SQLException e) {
            System.out.println("unable to do sql for: " + sql);
            e.printStackTrace();
            return;
        }

        try {
            //TODO: Load Ideas
            sql = "SELECT * FROM Ideas, ProjectIdea WHERE projectID = " + pId + " AND iId = ideaID";
            resultset = dbController.executeQuery(sql);
            while (resultset.next()) {
                int id = Integer.parseInt(resultset.getString("iId"));
                String desc = resultset.getString("iDesc");
                String author = resultset.getString("iIdeaAuthor");
                String ideaDate = resultset.getString("iIdeaDate");
                Boolean accepted = Integer.valueOf(resultset.getString("iAccepted")) == 1;
                Ideas newIdea = new Ideas(id, desc, author, ideaDate, accepted);
                if (accepted) obsListIdeaA.add(newIdea);
                else obsListIdeaP.add(newIdea);
            }
            listIdeaA.setItems(obsListIdeaA);
            listIdeaP.setItems(obsListIdeaP);
        } catch (SQLException e) {
            System.out.println("unable to do sql for: " + sql);
            e.printStackTrace();
            return;
        }
        dbController.closeDB();
    }

    private void switch_Level_on_Plugin(int level, Plugins plugin) {
        switch (level) {
            case 0: {
                obsListPurpose.add(plugin);
                break;
            }
            case 1: {
                obsListFeas.add(plugin);
                break;
            }
            case 2: {
                obsListQue.add(plugin);
                break;
            }
            case 3: {
                obsListDev.add(plugin);
                break;
            }
            case 4: {
                obsListFixes.add(plugin);
                break;
            }
            case 5: {
                obsListReleased.add(plugin);
                break;
            }
            /**
             * 0 - Suggested
             * 1 - Feasibility Check
             * 2 - Queued
             * 3 - Developing
             * 4 - Fixing
             * 5 - Released
             */
        }
    }

    private void populateChoicePlugins() {
        obsListChoicePlugins.clear();
        String sql = "";
        ResultSet resultset = null;
        try {
            //TODO: Come back to this.
            sql = "SELECT * FROM Plugins WHERE pId NOT IN (SELECT pluginID FROM ProjectPlugins WHERE projectID = " +
                    listProjects.getSelectionModel().getSelectedItem().getID() + ")";
            resultset = dbController.executeQuery(sql);
            while (resultset.next()) {
                int id = resultset.getInt("pId");
                String name = resultset.getString("pName");
                String desc = resultset.getString("pDesc");
                String ideaAuthor = resultset.getString("pIdeaAuthor");
                String pluginAuthor = resultset.getString("pPluginAuthor");
                String ideaDate = resultset.getString("pIdeaDate");
                String pluginDate = resultset.getString("pPluginDate");
                Boolean enabledByDefault = resultset.getBoolean("pEnabledByDefault");
                String requirements = resultset.getString("pRequirements");
                String todo = resultset.getString("pTODO");
                int level = resultset.getInt("pLevel");
                Plugins newPlugin = new Plugins(
                        name,
                        desc,
                        ideaAuthor,
                        ideaDate,
                        enabledByDefault,
                        requirements,
                        id,
                        pluginAuthor,
                        pluginDate,
                        level
                );
                obsListChoicePlugins.add(newPlugin);
            }
            choicePlugins.setItems(obsListChoicePlugins);
        } catch (SQLException e) {
            System.out.println("unable to do sql for: " + sql);
            e.printStackTrace();
            return;
        }
    }

    private void addPluginToProject(Plugins newValue) {
        int curProjID = listProjects.getSelectionModel().getSelectedItem().getID();
        msgDlgController.showError(
                "Add to Plugin to Project",
                "Are you sure you want to add " + newValue.getName() + "?",
                event -> {
                    String sql = "";
                    try {
                        sql = "INSERT INTO ProjectPlugins (pluginID, projectID) VALUES (" + newValue.getId() + "," + curProjID + ")";
                        dbController.execute(sql);
                        switch_Level_on_Plugin(newValue.getLevel(), newValue);
                        dbController.closeDB();
                    } catch (SQLException e) {
                        System.out.println("unable to do sql for: " + sql);
                    }
                });
    }

    private void populateChoiceIdeas() {
        getObsListChoiceIdeas.clear();
        String sql = "";
        ResultSet resultset = null;
        try {
            sql = "SELECT * FROM Ideas WHERE iId NOT IN (SELECT ideaID FROM ProjectIdea WHERE projectID = " +
                    listProjects.getSelectionModel().getSelectedItem().getID() + ")";
            resultset = dbController.executeQuery(sql);
            while (resultset.next()) {
                int id = resultset.getInt("iId");
                String desc = resultset.getString("iDesc");
                String name = resultset.getString("iIdeaAuthor");
                String date = resultset.getString("iIdeaDate");
                Boolean accepted = resultset.getBoolean("iAccepted");
                Ideas newIdea = new Ideas(id, desc, name, date, accepted);
                getObsListChoiceIdeas.add(newIdea);
            }
            choiceIdeas.setItems(getObsListChoiceIdeas);
        } catch (SQLException e) {
            System.out.println("unable to do sql for: " + sql);
            e.printStackTrace();
            return;
        }
    }

    private void addIdeaToProject(Ideas newValue) {
        int curProjID = listProjects.getSelectionModel().getSelectedItem().getID();
        msgDlgController.showError(
                "Add to Idea to Project",
                "Are you sure you want to add: " + newValue.getDesc(),
                event -> {
                    String sql = "";
                    try {
                        sql = "INSERT INTO ProjectIdea (projectID, ideaID) VALUES (" + curProjID + ", " + newValue.getId() + ")";
                        dbController.execute(sql);
                        if (newValue.isAccepted()) obsListIdeaA.add(newValue);
                        else obsListIdeaP.add(newValue);
                        dbController.closeDB();
                    } catch (SQLException e) {
                        System.out.println("unable to do sql for: " + sql);
                    }
                });
    }
    //</editor-fold>

    //<editor-fold desc="Buttons onAction">

    //<editor-fold desc="Plugin">
    public void setBtnAddPlugin(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/val/pp/views/PluginScreen.fxml"));
            Parent newScreen = loader.load();
            PluginEditorController pec = loader.getController();
            Stage newStage = App.initStageQuick(App.primaryStage, newScreen, "Plugin Information");
            Project selProject = listProjects.getSelectionModel().getSelectedItem();
            EventHandler<ActionEvent> event = event1 -> {
                String name = pec.tfName.getText();
                String desc = pec.taDesc.getText();
                String ideaAuth = pec.tfAuthorIdea.getText();
                String pluginAuth = pec.tfAuthorPlugin.getText();
                String ideaDate = "";
                String pluginDate = "";
                try {
                    ideaDate = pec.ideaDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (NullPointerException e) {
                    //ignore
                }
                try {
                    pluginDate = pec.pluginDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (NullPointerException e) {
                    //ignore
                }
                Boolean cbEnabled = pec.cbEnabled.isSelected();
                String req = pec.taRequirements.getText();
                //<editor-fold desc="ErrorCheck">
                int state = 0;
                if (name.equals("") || desc.equals("") || ideaAuth.equals("") || ideaDate.equals("")) {
                    msgDlgController.showError("Adding new Plugin Exception: Basic information", "Illegal Empty fields");
                    actionEvent.consume();
                    return;
                }
                if (!req.equals("") || cbEnabled) {
                    state = 1;
                }
                if (!pluginAuth.equals("") || !pluginDate.equals("")) {
                    if (pluginAuth.equals("") || pluginDate.equals("")) {
                        msgDlgController.showError("Adding new Plugin Exception: Plugin Author information", "Illegal Empty fields");
                        actionEvent.consume();
                        return;
                    } else state = 2;
                }
                //</editor-fold>
                String sql = "";
                try {
                    switch (state) {
                        case 0: {
                            sql = "INSERT INTO Plugins (pName,pDesc,pIdeaAuthor,pIdeaDate) VALUES " +
                                    "('" + name + "','" + desc + "','" + ideaAuth + "','" + ideaDate + "')";
                            break;
                        }
                        case 1: {
                            sql = "INSERT INTO Plugins (pName,pDesc,pIdeaAuthor,pIdeaDate,pEnabledByDefault,pRequirements) VALUES " +
                                    "('" + name + "','" + desc + "','" + ideaAuth + "','" + ideaDate + "','" + cbEnabled + "','" + req + "')";
                            break;
                        }
                        case 2: {
                            sql = "INSERT INTO Plugins (pName,pDesc,pIdeaAuthor,pPluginAuthor,pIdeaDate,pPluginDate,pEnabledByDefault,pRequirements) VALUES " +
                                    "('" + name + "','" + desc + "','" + ideaAuth + "','" + pluginAuth + "','" + ideaDate + "','" + pluginDate + "','" + cbEnabled + "','" + req + "')";
                            break;
                        }
                    }
                    dbController.execute(sql);
                    sql = "SELECT * FROM Plugins";
                    ResultSet resultSet = dbController.executeQuery(sql);
                    resultSet.last();
                    int id = resultSet.getInt("pId");
                    sql = "INSERT INTO ProjectPlugins (pluginID, projectID) VALUES ('" + id + "','" + selProject.getID() + "')";
                    dbController.execute(sql);
                    Plugins newPlugin = new Plugins(name, desc, ideaAuth, ideaDate, cbEnabled, req, pluginAuth, pluginDate);
                    newPlugin.setId(id);
                    obsListPurpose.add(newPlugin);
                    pec.done = true;
                    dbController.closeDB();
                } catch (SQLException e) {
                    System.out.println("unable to do sql for: " + sql);
                    e.printStackTrace();
                }
            };
            pec.fireEvent = event;
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBtnEditPlugin(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/val/pp/views/PluginScreen.fxml"));
            Parent newScreen = loader.load();
            PluginEditorController pec = loader.getController();
            Stage newStage = App.initStageQuick(App.primaryStage, newScreen, "Plugin Information");
            Project selProject = listProjects.getSelectionModel().getSelectedItem();
            Plugins curPlugin;
            try {
                if (curList.getValue() == null) throw new NullPointerException();
                curPlugin = (Plugins) curList.getValue().getItems().get(curList.getKey());
            } catch (ClassCastException e) {
                msgDlgController.showError("Editing Plugin Exception: No Plugin!", "Please select a plugin :)");
                return;
            } catch (NullPointerException e) {
                msgDlgController.showError("Editing Plugin Exception: No Plugin!", "Please select a plugin :)");
                return;
            }
            pec.tfName.setText(curPlugin.getName());
            pec.taDesc.setText(curPlugin.getDescription());
            pec.tfAuthorIdea.setText(curPlugin.getIdeaAuthor());//2018-06-01 08:24:40
            pec.ideaDatePicker.setValue(LocalDate.parse(curPlugin.getDateIdea(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            pec.tfAuthorPlugin.setText(curPlugin.getPluginAuthor());
            pec.pluginDatePicker.setValue(LocalDate.parse(curPlugin.getDatePlugin(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            pec.cbEnabled.setSelected(curPlugin.isEnabledByDefault());
            pec.taRequirements.setText(curPlugin.getRequirements());
            pec.tfAuthorIdea.setEditable(false);
            pec.ideaDatePicker.setEditable(false);
            EventHandler<ActionEvent> event = event1 -> {
                String name = pec.tfName.getText();
                String desc = pec.taDesc.getText();
                String ideaAuth = pec.tfAuthorIdea.getText();//2018-06-01 08:24:40
                String pluginAuth = pec.tfAuthorPlugin.getText();
                Boolean cbEnabled = pec.cbEnabled.isSelected();
                String req = pec.taRequirements.getText();
                String ideaDate = "";
                String pluginDate = "";
                try {
                    ideaDate = pec.ideaDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (NullPointerException e) {
                    //ignore
                }
                try {
                    pluginDate = pec.pluginDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (NullPointerException e) {
                    //ignore
                }
                //<editor-fold desc="ErrorCheck">
                int state = 0;
                if (name.equals("") || desc.equals("") || ideaAuth.equals("") || ideaDate.equals("")) {
                    msgDlgController.showError("Updating Plugin Exception: Basic information", "Illegal Empty fields");
                    actionEvent.consume();
                    return;
                }
                if (!req.equals("") || cbEnabled) {
                    state = 1;
                }
                if (!pluginAuth.equals("") || !pluginDate.equals("null")) {
                    if (pluginAuth.equals("") || pluginDate.equals("null")) {
                        msgDlgController.showError("Updating Plugin Exception: Plugin Author information", "Illegal Empty fields");
                        actionEvent.consume();
                        return;
                    } else state = 2;
                }
                //</editor-fold>
                String sql = "";
                try {
                    switch (state) {
                        case 0: {
                            sql = "UPDATE Plugins " +
                                    "SET pName = '" + name + "', pDesc = '" + desc + "', pIdeaAuthor = '" + ideaAuth + "', pIdeaDate = '" + ideaDate + "' " +
                                    "WHERE pId = " + curPlugin.getId() + "";
                            break;
                        }
                        case 1: {
                            sql = "UPDATE Plugins " +
                                    "SET pName = '" + name + "', pDesc = '" + desc + "', pIdeaAuthor = '" + ideaAuth + "', pIdeaDate = '" + ideaDate + "' " +
                                    ", pEnabledByDefault = " + cbEnabled + ", pRequirements = '" + req + "'" +
                                    "WHERE pId = " + curPlugin.getId() + "";
                            break;
                        }
                        case 2: {
                            sql = "UPDATE Plugins " +
                                    "SET pName = '" + name + "', pDesc = '" + desc + "', pIdeaAuthor = '" + ideaAuth + "', pIdeaDate = '" + ideaDate + "' " +
                                    ", pEnabledByDefault = " + cbEnabled + ", pRequirements = '" + req + "'" +
                                    ", pPluginAuthor = '" + pluginAuth + "', pPluginDate = '" + pluginDate + "'" +
                                    "WHERE pId = " + curPlugin.getId() + "";
                            break;
                        }
                    }
                    dbController.execute(sql);
                    curPlugin.setName(name);
                    curPlugin.setDescription(desc);
                    curPlugin.setIdeaAuthor(ideaAuth);
                    curPlugin.setDateIdea(ideaDate);
                    curPlugin.setEnabledByDefault(cbEnabled);
                    curPlugin.setRequirements(req);
                    curPlugin.setPluginAuthor(pluginAuth);
                    curPlugin.setDatePlugin(pluginDate);
                    //  (Plugins) curList.getValue().getItems().get(curList.getKey());
                    pec.done = true;
                    dbController.closeDB();
                } catch (SQLException e) {
                    System.out.println("unable to do sql for: " + sql);
                    e.printStackTrace();
                }
            };
            pec.fireEvent = event;
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBtnDelPlugin(ActionEvent actionEvent) {
        Plugins curPlugin;
        int index;
        ListView<Plugins> curListView;
        try {
            curListView = curList.getValue();
            if (curListView == null) throw new NullPointerException();
            curPlugin = curListView.getItems().get(curList.getKey());
            if (curListView.getItems().size() <= 1) throw new IllegalAccessException();
            index = curList.getKey() > 0 ? 0 : 1;
        } catch (ClassCastException e) {
            msgDlgController.showError("Deleting Plugin Exception: No Plugin!", "Please select a plugin :)");
            return;
        } catch (NullPointerException e) {
            msgDlgController.showError("Deleting Plugin Exception: No Plugin!", "Please select a plugin :)");
            return;
        } catch (IllegalAccessException e) {
            msgDlgController.showError("Deleting Plugin Exception: Not enough Plugins!", "Cannot Delete any more plugins.");
            return;
        }

        msgDlgController.showError("Deleting Plugin", "Are you sure you want to delete " + curPlugin.getName() + "?", event -> {
            String sql = "";
            try {
                sql = "DELETE FROM ProjectPlugins WHERE pluginID = " + curPlugin.getId() + "";
                dbController.execute(sql);
                sql = "DELETE FROM Plugins WHERE pId = " + curPlugin.getId() + "";
                dbController.execute(sql);
                curListView.getSelectionModel().select(index);
                curListView.getItems().remove(curPlugin);
                dbController.closeDB();
            } catch (SQLException e) {
                System.out.println("unable to do sql for: " + sql);
            }
        });
    }
    //</editor-fold>

    //<editor-fold desc="Project">
    public void setBtnAddProj(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/val/pp/views/ProjectScreen.fxml"));
            Parent newScreen = loader.load();
            ProjectEditerController pec = loader.getController();
            Stage newStage = App.initStageQuick(App.primaryStage, newScreen, "Project Information");
            EventHandler<ActionEvent> event = event1 -> {
                String name = pec.tfName.getText();
                String owner = pec.tfOwner.getText();
                String server = pec.tfServer.getText();
                String desc = pec.taDesc.getText();
                //<editor-fold desc="ErrorCheck">
                if (name.equals("") || owner.equals("") || server.equals("") || desc.equals("")) {
                    msgDlgController.showError("Adding new Project Exception: Basic information", "Illegal Empty fields");
                    actionEvent.consume();
                    return;
                }
                //</editor-fold>
                String sql = "";
                try {
                    sql = "INSERT INTO Projects (pName,pDesc,pServer,pOwner) VALUES ('" + name + "','" + owner + "','" + server + "','" + desc + "')";
                    dbController.execute(sql);
                    Project newProj = new Project(name, desc, server, owner);
                    sql = "SELECT * FROM Projects";
                    ResultSet resultSet = dbController.executeQuery(sql);
                    resultSet.last();
                    int id = resultSet.getInt("pId");
                    newProj.setID(id);
                    obsListProjects.add(newProj);
                    dbController.closeDB();
                    pec.done = true;
                } catch (SQLException e) {
                    System.out.println("unable to do sql for: " + sql);
                }
            };
            pec.fireEvent = event;
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBtnEditProj(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/val/pp/views/ProjectScreen.fxml"));
            Parent newScreen = loader.load();
            ProjectEditerController pec = loader.getController();
            Stage newStage = App.initStageQuick(App.primaryStage, newScreen, "Project Information");
            Project curProj = listProjects.getSelectionModel().getSelectedItem();
            pec.tfName.setText(curProj.getName());
            pec.tfOwner.setText(curProj.getpOwner());
            pec.tfServer.setText(curProj.getpServer());
            pec.taDesc.setText(curProj.getDesc());
            EventHandler<ActionEvent> event = event1 -> {
                String name = pec.tfName.getText();
                String owner = pec.tfOwner.getText();
                String server = pec.tfServer.getText();
                String desc = pec.taDesc.getText();
                //<editor-fold desc="ErrorCheck">
                if (name.equals("") || owner.equals("") || server.equals("") || desc.equals("")) {
                    msgDlgController.showError("Adding new Project Exception: Basic information", "Illegal Empty fields");
                    actionEvent.consume();
                    return;
                }
                //</editor-fold>
                String sql = "";
                try {
                    sql = "UPDATE Projects SET " +
                            "pName = '" + name + "'," +
                            "pDesc = '" + desc + "'," +
                            "pServer = '" + server + "'," +
                            "pOwner = '" + owner + "' " +
                            "WHERE pId = " + curProj.getID() + "";
                    dbController.execute(sql);
                    curProj.setName(name);
                    curProj.setDesc(desc);
                    curProj.setpOwner(owner);
                    curProj.setpOwner(server);
                    dbController.closeDB();
                    pec.done = true;
                } catch (SQLException e) {
                    System.out.println("unable to do sql for: " + sql);
                }
            };
            pec.fireEvent = event;
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBtnDelProj(ActionEvent actionEvent) {
        Project curProj = listProjects.getSelectionModel().getSelectedItem();
        if (listProjects.getItems().size() <= 1) {
            msgDlgController.showError("Deleting Project Exception: Not enough Projects!", "Cannot Delete any more Projects.");
            return;
        }
        int index = listProjects.getSelectionModel().getSelectedIndex();
        index = index > 0 ? 0 : 1;
        String sql = "";
        try {
            sql = "DELETE FROM ProjectPlugins WHERE projectID = " + curProj.getID() + "";
            dbController.execute(sql);
            sql = "DELETE FROM ProjectIdeas WHERE projectID = " + curProj.getID() + "";
            dbController.execute(sql);
            sql = "DELETE FROM Projects WHERE pId = " + listProjects.getSelectionModel().getSelectedItem().getID() + "";
            dbController.execute(sql);
            listProjects.getSelectionModel().select(index);
            listProjects.getItems().remove(curProj);
            dbController.closeDB();
        } catch (SQLException e) {
            System.out.println("unable to do sql for: " + sql);
        }
    }
    //</editor-fold>

    //<editor-fold desc="Idea">
    public void setBtnAddIdea(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/val/pp/views/IdeaScreen.fxml"));
            Parent newScreen = loader.load();
            IdeaController ic = loader.getController();
            Stage newStage = App.initStageQuick(App.primaryStage, newScreen, "Idea Information");
            Project curProj = listProjects.getSelectionModel().getSelectedItem();
            EventHandler<ActionEvent> event = event1 -> {
                String name = ic.tfName.getText();
                String desc = ic.taDesc.getText();
                String ideaDate;
                try {
                    ideaDate = ic.datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (NullPointerException e) {
                    msgDlgController.showError("Adding Idea Exception: Dating problems", "You require a date.");
                    actionEvent.consume();
                    return;
                }
                String sql = "";
                try {
                    sql = "INSERT INTO Ideas (iDesc,iIdeaAuthor,iIdeaDate,iAccepted) " +
                            "VALUES ('" + desc + "','" + name + "','" + ideaDate + "',0)";
                    dbController.execute(sql);
                    Ideas newIdea = new Ideas(desc, name, ideaDate);
                    sql = "SELECT * FROM Ideas";
                    ResultSet resultSet = dbController.executeQuery(sql);
                    resultSet.last();
                    int id = resultSet.getInt("iId");
                    newIdea.setId(id);
                    sql = "INSERT INTO ProjectIdea (projectID, ideaID) VALUES (" + curProj.getID() + "," + id + ")";
                    dbController.execute(sql);
                    obsListIdeaP.add(newIdea);
                    ic.done = true;
                    dbController.closeDB();
                } catch (SQLException e) {
                    System.out.println("unable to do sql for: " + sql);
                }
            };
            ic.fireEvent = event;
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBtnEditIdea(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/val/pp/views/IdeaScreen.fxml"));
            Parent newScreen = loader.load();
            IdeaController ic = loader.getController();
            Stage newStage = App.initStageQuick(App.primaryStage, newScreen, "Idea Information");
            Project curProj = listProjects.getSelectionModel().getSelectedItem();
            Ideas curIdea;
            try {
                if (curList.getValue() == null) throw new NullPointerException();
                curIdea = (Ideas) curList.getValue().getItems().get(curList.getKey());
            } catch (ClassCastException e) {
                msgDlgController.showError("Editing Idea Exception: No Idea!", "Please select an Idea :)");
                return;
            } catch (NullPointerException e) {
                msgDlgController.showError("Editing Idea Exception: No Idea!", "Please select an Idea :)");
                return;
            }
            ic.tfName.setText(curIdea.getAuthor());
            ic.taDesc.setText(curIdea.getDesc());
            ic.datePicker.setValue(LocalDate.parse(curIdea.getIdeaDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            EventHandler<ActionEvent> event = event1 -> {
                String name = ic.tfName.getText();
                String desc = ic.taDesc.getText();
                String ideaDate;
                try {
                    ideaDate = ic.datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (NullPointerException e) {
                    msgDlgController.showError("Editing Idea Exception: Dating problems", "You require a date.");
                    actionEvent.consume();
                    return;
                }
                String sql = "";
                try {
                    sql = "UPDATE Ideas SET " +
                            "iDesc = '" + desc + "', " +
                            "iIdeaAuthor = '" + name + "', " +
                            "iIdeaDate = '" + ideaDate + "', " +
                            "iAccepted = " + curIdea.isAccepted() +
                            " WHERE iId = " + curIdea.getId() + "";
                    dbController.execute(sql);
                    curIdea.setAuthor(name);
                    curIdea.setDesc(desc);
                    curIdea.setIdeaDate(ideaDate);
                    ic.done = true;
                    dbController.closeDB();
                } catch (SQLException e) {
                    System.out.println("unable to do sql for: " + sql);
                }
            };
            ic.fireEvent = event;
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBtnDelIdea(ActionEvent actionEvent) {
        Ideas curIdea;
        ListView<Ideas> curListView;
        int curProjId = listProjects.getSelectionModel().getSelectedItem().getID();
        try {
            curListView = curList.getValue();
            if (curListView == null) throw new NullPointerException();
            curIdea = curListView.getItems().get(curList.getKey());
        } catch (ClassCastException e) {
            msgDlgController.showError("Deleting Idea Exception: No Idea!", "Please select an Idea :)");
            return;
        } catch (NullPointerException e) {
            msgDlgController.showError("Deleting Idea Exception: No Idea!", "Please select an Idea :)");
            return;
        }

        msgDlgController.showError("Deleting Plugin", "Are you sure you want to delete: " + curIdea.getDesc(), event -> {
            String sql = "";
            try {
                sql = "DELETE FROM ProjectIdea WHERE projectID = " + curProjId + " AND ideaID = " + curIdea.getId() + "";
                dbController.execute(sql);
                sql = "DELETE FROM Ideas WHERE iId = " + curIdea.getId() + "";
                dbController.execute(sql);
                curListView.getSelectionModel().select(-1);
                curListView.getItems().remove(curIdea);
                dbController.closeDB();
            } catch (SQLException e) {
                System.out.println("unable to do sql for: " + sql);
            }
        });
    }
    //</editor-fold>

    //</editor-fold>
}