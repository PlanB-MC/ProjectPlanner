package val.pp.Model;

import javafx.beans.property.*;

public class Plugins {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty ideaAuthor = new SimpleStringProperty();
    private StringProperty pluginAuthor = new SimpleStringProperty();
    private StringProperty dateIdea = new SimpleStringProperty();
    private StringProperty datePlugin = new SimpleStringProperty();
    private BooleanProperty enabledByDefault = new SimpleBooleanProperty();
    private StringProperty requirements = new SimpleStringProperty();
    private StringProperty todo = new SimpleStringProperty();
    private IntegerProperty level = new SimpleIntegerProperty();

    public Plugins(String name, String description, String ideaAuthor, String dateIdea) {
        this.name.setValue(name);
        this.description.setValue(description);
        this.ideaAuthor.setValue(ideaAuthor);
        this.dateIdea.setValue(dateIdea);
    }

    public Plugins(String name, String description, String ideaAuthor, String dateIdea,
                   boolean enabledByDefault, String requirements) {
        this(name, description, ideaAuthor, dateIdea);
        this.enabledByDefault.setValue(enabledByDefault);
        this.requirements.setValue(requirements);
    }

    public Plugins(String name, String description, String ideaAuthor, String dateIdea,
                   boolean enabledByDefault, String requirements, int id,
                   String pluginAuthor, String datePlugin, int level) {
        this(name, description, ideaAuthor, dateIdea, enabledByDefault, requirements);
        this.pluginAuthor.setValue(pluginAuthor);
        this.datePlugin.setValue(datePlugin);
        this.id.setValue(id);
    }

    public int getLevel() {
        return level.get();
    }

    public void setLevel(int level) {

        this.level.set(level);
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    @Override
    public String toString() {
        return name.getValue();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {

        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getTodo() {
        return todo.get();
    }

    public void setTodo(String todo) {
        this.todo.set(todo);
    }

    public StringProperty todoProperty() {
        return todo;
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

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getPluginAuthor() {
        return pluginAuthor.get();
    }

    public void setPluginAuthor(String pluginAuthor) {
        this.pluginAuthor.set(pluginAuthor);
    }

    public StringProperty pluginAuthorProperty() {
        return pluginAuthor;
    }

    public String getDatePlugin() {
        return datePlugin.get();
    }

    public void setDatePlugin(String datePlugin) {
        this.datePlugin.set(datePlugin);
    }

    public StringProperty datePluginProperty() {
        return datePlugin;
    }

    public boolean isEnabledByDefault() {
        return enabledByDefault.get();
    }

    public void setEnabledByDefault(boolean enabledByDefault) {
        this.enabledByDefault.set(enabledByDefault);
    }

    public BooleanProperty enabledByDefaultProperty() {
        return enabledByDefault;
    }

    public String getRequirements() {
        return requirements.get();
    }

    public void setRequirements(String requirements) {
        this.requirements.set(requirements);
    }

    public StringProperty requirementsProperty() {
        return requirements;
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
