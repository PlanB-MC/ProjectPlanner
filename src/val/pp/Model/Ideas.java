package val.pp.Model;

import javafx.beans.property.*;

public class Ideas {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty desc = new SimpleStringProperty();
    private StringProperty author = new SimpleStringProperty();
    private StringProperty ideaDate = new SimpleStringProperty();
    private BooleanProperty accepted = new SimpleBooleanProperty();

    public Ideas(String desc, String author, String ideaDate) {
        this.desc.setValue(desc);
        this.author.setValue(author);
        this.ideaDate.setValue(ideaDate);
        this.accepted.setValue(false);
    }

    @Override
    public String toString() {
        return desc.getValue();
    }

    public Ideas(int id, String desc, String author, String ideaDate, boolean accepted) {
        this(desc,author,ideaDate);
        this.accepted.setValue(accepted);
        this.id.setValue(id);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
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

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public StringProperty authorProperty() {
        return author;
    }

    public String getIdeaDate() {
        return ideaDate.get();
    }

    public void setIdeaDate(String ideaDate) {
        this.ideaDate.set(ideaDate);
    }

    public StringProperty ideaDateProperty() {
        return ideaDate;
    }

    public boolean isAccepted() {
        return accepted.get();
    }

    public BooleanProperty acceptedProperty() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted.set(accepted);
    }
}
