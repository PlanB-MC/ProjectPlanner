package val.pp.Model.ccBoxList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class valBox extends Group {
    public static double sizeH = 20;
    public static double sizeW = 100;
    private Rectangle rectangle;
    private Label lblName;
    public DoubleProperty yAnchor;

    public valBox(double posX, double posY, String name) {
        setLayoutX(posX);
        setLayoutY(posY);
        rectangle = new Rectangle(sizeW, sizeH);
        rectangle.setFill(Color.BLACK);
        lblName = new Label(name);
        lblName.setTextFill(Color.WHITE);
        lblName.setLayoutX(10);
        lblName.setLayoutY(1);
        this.getChildren().addAll(rectangle, lblName);
        yAnchor = new SimpleDoubleProperty();

        setOnMousePressed(event -> {
            yAnchor.setValue(event.getScreenY() - getLayoutY());
        });
        setOnMouseDragged(event -> {
            setLayoutY(event.getScreenY()-yAnchor.getValue());
        });
    }
}
