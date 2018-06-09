package val.pp.Model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Plugins {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty ideaAuthor = new SimpleStringProperty();
    private StringProperty pluginAuthor = new SimpleStringProperty();
    private StringProperty dateIdea = new SimpleStringProperty();
    private StringProperty datePlugin = new SimpleStringProperty();
    private BooleanProperty enabledByDefault = new SimpleBooleanProperty();
    private StringProperty requirements = new SimpleStringProperty();

    public Plugins(String name, String description, String ideaAuthor, String dateIdea) {
        this.name.setValue(name);
        this.description.setValue(description);
        this.ideaAuthor.setValue(ideaAuthor);
        this.dateIdea.setValue(dateIdea);
    }

    public Plugins(String name, String description, String ideaAuthor, String dateIdea,
                   boolean enabledByDefault, String requirements) {
        this(name,description,ideaAuthor,dateIdea);
        this.enabledByDefault.setValue(enabledByDefault);
        this.requirements.setValue(requirements);
    }

    public Plugins(String name, String description, String ideaAuthor, String dateIdea,
                   boolean enabledByDefault, String requirements,
                   String pluginAuthor, String datePlugin) {
        this(name,description,ideaAuthor,dateIdea,enabledByDefault,requirements);
        this.pluginAuthor.setValue(pluginAuthor);
        this.datePlugin.setValue(datePlugin);
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

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getPluginAuthor() {
        return pluginAuthor.get();
    }

    public StringProperty pluginAuthorProperty() {
        return pluginAuthor;
    }

    public void setPluginAuthor(String pluginAuthor) {
        this.pluginAuthor.set(pluginAuthor);
    }

    public String getDatePlugin() {
        return datePlugin.get();
    }

    public StringProperty datePluginProperty() {
        return datePlugin;
    }

    public void setDatePlugin(String datePlugin) {
        this.datePlugin.set(datePlugin);
    }

    public boolean isEnabledByDefault() {
        return enabledByDefault.get();
    }

    public BooleanProperty enabledByDefaultProperty() {
        return enabledByDefault;
    }

    public void setEnabledByDefault(boolean enabledByDefault) {
        this.enabledByDefault.set(enabledByDefault);
    }

    public String getRequirements() {
        return requirements.get();
    }

    public StringProperty requirementsProperty() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements.set(requirements);
    }

    public String getIdeaAuthor() {
        return ideaAuthor.get();
    }

    public StringProperty ideaAuthorProperty() {
        return ideaAuthor;
    }

    public String getDateIdea() {
        return dateIdea.get();
    }

    public StringProperty dateIdeaProperty() {
        return dateIdea;
    }
}
