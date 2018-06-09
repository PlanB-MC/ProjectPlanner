package val.pp.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Project {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty desc = new SimpleStringProperty();

    public Project(String name, String desc) {
        this.name.set(name);
        this.desc.set(desc);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDesc() {
        return desc.get();
    }

    public StringProperty descProperty() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
