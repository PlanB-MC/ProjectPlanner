package val.pp.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Project {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty desc = new SimpleStringProperty();
    private StringProperty pServer = new SimpleStringProperty();
    private StringProperty pOwner = new SimpleStringProperty();
    private IntegerProperty ID = new SimpleIntegerProperty();

    public Project(String name, String desc, String pServer, String pOwner) {
        this.name.setValue(name);
        this.desc.setValue(desc);
        this.pServer.setValue(pServer);
        this.pOwner.setValue(pOwner);
    }

    @Override
    public String toString() {
        return name.getValue();
    }

    public Project(String name, String desc, String pServer, String pOwner, int ID) {
        this(name, desc, pServer, pOwner);
        this.ID.setValue(ID);
    }

    public String getpServer() {
        return pServer.get();
    }

    public void setpServer(String pServer) {
        this.pServer.set(pServer);
    }

    public StringProperty pServerProperty() {
        return pServer;
    }

    public String getpOwner() {
        return pOwner.get();
    }

    public void setpOwner(String pOwner) {
        this.pOwner.set(pOwner);
    }

    public StringProperty pOwnerProperty() {
        return pOwner;
    }

    public int getID() {
        return ID.get();
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getDesc() {
        return desc.get();
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }

    public StringProperty descProperty() {
        return desc;
    }

}
