package val.pp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import val.pp.Model.Ideas;
import val.pp.Model.Plugins;
import val.pp.Model.Project;
import val.pp.preload.App;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private ListView curList = null;
    private ObservableList<Project> obsListProjects = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListReleased = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListFixes = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListDev = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListQue = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListFeas = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Plugins> obsListPurpose = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Ideas> obsListIdeaA = FXCollections.observableArrayList(new ArrayList<>());
    private ObservableList<Ideas> obsListIdeaP = FXCollections.observableArrayList(new ArrayList<>());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TEST();
        initLoadFromDB();
        doBindings();
        refreshPluginList();
        refreshProjectList();

    }

    private void doBindings() {
        //Plugins
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
            setCur(listReleased);
            changeTXTinfo(oldValue, newValue);
        });
        listFixes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCur(listFixes);
            changeTXTinfo(oldValue, newValue);
        });
        listDev.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCur(listDev);
            changeTXTinfo(oldValue, newValue);
        });
        listQue.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCur(listQue);
            changeTXTinfo(oldValue, newValue);
        });
        listFeas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCur(listFeas);
            changeTXTinfo(oldValue, newValue);
        });
        listPurpose.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCur(listPurpose);
            changeTXTinfo(oldValue, newValue);
        });

        listReleased.getSelectionModel().selectedIndexProperty();
        listFixes.getSelectionModel().selectedIndexProperty();
        listDev.getSelectionModel().selectedIndexProperty();
        listQue.getSelectionModel().selectedIndexProperty();
        listFeas.getSelectionModel().selectedIndexProperty();
        listPurpose.getSelectionModel().selectedIndexProperty();

        //Ideas
        listIdeaA.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) listIdeaA.getSelectionModel().select(-1);
        });
        listIdeaP.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) listIdeaP.getSelectionModel().select(-1);
        });

        listIdeaA.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCur(listIdeaA);
            changeTXTinfo(oldValue, newValue);
        });
        listIdeaP.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCur(listIdeaP);
            changeTXTinfo(oldValue, newValue);
        });

        listIdeaA.getSelectionModel().selectedIndexProperty();
        listIdeaP.getSelectionModel().selectedIndexProperty();


        //Projects
        listProjects.getSelectionModel().selectedIndexProperty();

        listProjects.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            changeTXTinfo(oldValue, newValue);
            loadFromDB_PluginIdeas(listProjects.getSelectionModel().getSelectedItem().getID());
        });

        //update TextAreas
        txtProjectInfo.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshProjectList();
        });

        txtPluginInfo.textProperty().addListener((observable, oldValue, newValue) -> {
            refreshProjectList();
        });

        listProjects.getSelectionModel().selectFirst();
    }

    private void setCur(ListView listReleased) {
        curList = listReleased;
    }

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

        if (curList == listReleased && indexLR >= 0) {
            listReleased.fireEvent(new ListView.EditEvent<>(
                            listReleased, ListView.editCommitEvent(), obsListReleased.get(indexLR), indexLR
                    )
            );
        }
        if (curList == listFixes && indexLFX >= 0) {
            listFixes.fireEvent(new ListView.EditEvent<>(
                            listFixes, ListView.editCommitEvent(), obsListFixes.get(indexLFX), indexLFX
                    )
            );
        }
        if (curList == listDev && indexLD >= 0) {
            listDev.fireEvent(new ListView.EditEvent<>(
                            listDev, ListView.editCommitEvent(), obsListDev.get(indexLD), indexLD
                    )
            );
        }
        if (curList == listQue && indexLQ >= 0) {
            listQue.fireEvent(new ListView.EditEvent<>(
                            listQue, ListView.editCommitEvent(), obsListQue.get(indexLQ), indexLQ
                    )
            );
        }
        if (curList == listFeas && indexLF >= 0) {
            listFeas.fireEvent(new ListView.EditEvent<>(
                            listFeas, ListView.editCommitEvent(), obsListFeas.get(indexLF), indexLF
                    )
            );
        }
        if (curList == listPurpose && indexLP >= 0) {
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

        if (curList == listIdeaA && indexIA >= 0) {
            listIdeaA.fireEvent(new ListView.EditEvent<>(
                            listIdeaA, ListView.editCommitEvent(), obsListIdeaA.get(indexIA), indexIA
                    )
            );
        }
        if (curList == listIdeaP && indexIP >= 0) {
            listIdeaP.fireEvent(new ListView.EditEvent<>(
                            listIdeaP, ListView.editCommitEvent(), obsListIdeaP.get(indexIP), indexIP
                    )
            );
        }

        listIdeaA.getSelectionModel().select(indexIA);
        listIdeaP.getSelectionModel().select(indexIP);
    }

    private void TEST() {
        //SAMPLES
        /*String desc = "Lorus Ispum sup sup. Soek jy n broodjie?";
        Project numOne = new Project("TEST", desc + "1");
        Project numTwo = new Project("IS IT?", desc + "2");
        Project numThree = new Project("BLEH", desc + "3");
        obsListProjects.addAll(numOne, numTwo, numThree);*/

       /* valBox numbaOee = new valBox(20, 20, "AWE");
        valBox qwe = new valBox(20, 50, "AWE");
        valBox numdgsbaOee = new valBox(20, 70, "AWE");
        valBox numfhbaOee = new valBox(20, 100, "AWE");
        paneLeft.getChildren().addAll(numbaOee, qwe, numdgsbaOee, numfhbaOee);*/


        // valPane vp = new valPane(paneLeft);
    }

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
                int id = Integer.parseInt(resultset.getString("pId"));
                String name = resultset.getString("pName");
                String desc = resultset.getString("pDesc");
                String ideaAuthor = resultset.getString("pIdeaAuthor");
                String pluginAuthor = resultset.getString("pPluginAuthor");
                String ideaDate = resultset.getString("pIdeaDate");
                String pluginDate = resultset.getString("pPluginDate");
                Boolean enabledByDefault = Integer.valueOf(resultset.getString("pEnabledByDefault")) == 1;
                String requirements = resultset.getString("pRequirements");
                String todo = resultset.getString("pTODO");
                int level = Integer.parseInt(resultset.getString("pLevel"));
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
                switch (level) {
                    case 0: {
                        obsListPurpose.add(newPlugin);
                        break;
                    }
                    case 1: {
                        obsListFeas.add(newPlugin);
                        break;
                    }
                    case 2: {
                        obsListQue.add(newPlugin);
                        break;
                    }
                    case 3: {
                        obsListDev.add(newPlugin);
                        break;
                    }
                    case 4: {
                        obsListFixes.add(newPlugin);
                        break;
                    }
                    case 5: {
                        obsListReleased.add(newPlugin);
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
                listPurpose.setItems(obsListPurpose);
                listFeas.setItems(obsListFeas);
                listQue.setItems(obsListQue);
                listDev.setItems(obsListDev);
                listFixes.setItems(obsListFixes);
                listReleased.setItems(obsListReleased);
            }
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

    public void setBtnAddPlugin(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/val/pp/views/ProjectScreen.fxml"));
            Parent newScreen = loader.load();
            PluginEditorController pec = loader.getController();
            Stage newStage = App.initStageQuick(App.primaryStage, newScreen, "Plugin Information");
            pec.obsListProj = obsListProjects;
            newStage.onCloseRequestProperty().addListener((observable, oldValue, newValue) -> {
                String name = pec.tfName.getText();
                String desc = pec.taDesc.getText();
                String ideaAuth = pec.tfAuthorIdea.getText();//2018-06-01 08:24:40
                String ideaDate = pec.ideaDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
                String pluginAuth = pec.tfAuthorPlugin.getText();
                String pluginDate = pec.pluginDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
                Boolean cbEnabled = pec.cbEnabled.isSelected();
                String req = pec.taRequirements.getText();
                Project selProject = pec.lbProjects.getSelectionModel().getSelectedItem();
                String sql = "";
                try {
                    sql = "INSERT INTO Plugins (pName,pDesc,pIdeaAuthor,pPluginAuthor,pIdeaDate,pPluginDate,pEnabledByDefault,pRequirements) VALUES " +
                            "('" + name + "','" + desc + "','" + ideaAuth + "','" + pluginAuth + "','" + ideaDate + "','" + pluginDate + "','" + cbEnabled + "','" + req + "')";
                    dbController.execute(sql);
                    sql = "SELECT * FROM Plugins";
                    ResultSet resultSet = dbController.executeQuery(sql);
                    resultSet.last();
                    int id = resultSet.getInt("pId");
                    sql = "INSERT INTO ProjectPlugins (pluginID, projectID) VALUES ('" + id + "','" + selProject.getID() + "')";
                    dbController.execute(sql);
                    Plugins newPlugin = new Plugins(name, desc, ideaAuth, ideaDate, cbEnabled, req);
                    newPlugin.setId(id);
                    obsListPurpose.add(newPlugin);
                    dbController.closeDB();
                } catch (SQLException e) {
                    System.out.println("unable to do sql for: " + sql);
                }

            });
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBtnEditPlugin(ActionEvent actionEvent) {

    }

    public void setBtnDelPlugin(ActionEvent actionEvent) {

    }

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
                String sql = "";
                try {
                    sql = "INSERT INTO Projects (pName,pDesc,pServer,pOwner) VALUES ('" + name + "','" + owner + "','" + server + "','" + desc + "')";
                    dbController.execute(sql);
                    Project newProj = new Project(name, desc, server, owner);
                    sql = "SELECT * FROM Projects";
                    ResultSet resultSet = dbController.executeQuery(sql);
                    resultSet.last();
                    int id = resultSet.getInt("pId");
                    obsListProjects.add(newProj);
                    dbController.closeDB();
                } catch (SQLException e) {
                    System.out.println("unable to do sql for: " + sql);
                }
            };
            pec.fireEvnt = event;
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
                String sql = "";
                try {
                    sql = "UPDATE Projects SET " +
                            "pName = '" + name + "'," +
                            "pDesc = '" + desc + "'," +
                            "pServer = '" + server + "'," +
                            "pOwner = '" + owner + "' " +
                            "WHERE pId = "+curProj.getID()+"";
                    dbController.execute(sql);
                    curProj.setName(name);
                    curProj.setDesc(desc);
                    curProj.setpOwner(owner);
                    curProj.setpOwner(server);
                    dbController.closeDB();
                } catch (SQLException e) {
                    System.out.println("unable to do sql for: " + sql);
                }
            };
            pec.fireEvnt = event;
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBtnDelProj(ActionEvent actionEvent) {

    }

    public void setBtnAddIdea(ActionEvent actionEvent) {

    }

    public void setBtnEditIdea(ActionEvent actionEvent) {

    }
}
