package val.pp.Model;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class valBox extends Group {
    private Rectangle rectangle;
    private Label lblName;
    private double yAnchor;

    public valBox(double posX, double posY, String name) {
        rectangle = new Rectangle(posX, posY, 100, 20);
        rectangle.setFill(Color.BLACK);
        lblName = new Label(name);
        lblName.setTextFill(Color.WHITE);
        lblName.setLayoutX(posX + 10);
        lblName.setLayoutY(posY + 1);
        this.getChildren().addAll(rectangle, lblName);

        setOnMousePressed(event -> {
            yAnchor = event.getScreenY() - getLayoutY();
        });
        setOnMouseDragged(event -> {
            setLayoutY(event.getScreenY()-yAnchor);
        });
    }
}
